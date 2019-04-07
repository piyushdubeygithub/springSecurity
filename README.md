# skulocity-opentaps

# Software Required
	1. JDK 7 Oracle
	2. Postgresql 9.x

# Installation Steps
	1. Install JDK-7
		sudo apt-get install python-software-properties
		sudo add-apt-repository ppa:webupd8team/java
		sudo apt-get update
		sudo apt-get install oracle-java7-installer
	2. Set JAVA_HOME
		$ sudo vim ~/.bashrc
		>Add these at the end of file
		export JAVA_HOME="/usr/lib/jvm/java-7-oracle"
		export JRE_HOME="/usr/lib/jvm/java-7-oracle/jre"
		PATH=$JAVA_HOME/bin:$JRE_HOME:$PATH
		export CLASSPATH=$PATH

	3. Install Postgresql
		$ sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'
		$ wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | sudo apt-key add -
		$ sudo apt-get update
		$ sudo apt-get install postgresql postgresql-contrib
		$ psql -hlocalhost -Upostgres
		$postgres$ CREATE USER skulocity WITH PASSWORD ‘skulocity’;
		$postgres$ CREATE DATABASE skulocity OWNER skulocity;


	4. Edit opentaps files(no need to follow these steps if not installing a fresh opentaps)
		$ vim opentaps-1.5.0/framework/entity/config/entityengine.xml
		>locate localpostgres
		> change database name-skulocity
		>change user name-skulocity
		>change password - skulocity
		>locate <delegator name="default"
		> change datasource-name attribute value in group-name=”org.ofbiz” tag to localpostgres
		$ vim opentaps-1.5.0/framework/base/config/ofbiz-container.xml
		>locate 8080 to change port
		$ ./ant run-install-extseed
		$ ./ant create-admin-user-login
	
	5. Go to project root folder and execute this command
		$ ./ant run-install
		> it will result in BUILD SUCCESSFUL
        
	7. For Development purpose only
		> Add below line just above # shutdown settings
		  fuser -n tcp -k 10523
		  ./ant
		> Comment this line(this is second last line)
		  $JAVA $VMARGS -jar ofbiz.jar $* >>$OFBIZ_LOG 2>>$OFBIZ_LOG&
		> Uncomment Last Line i.e
		  exec "$JAVA" $VMARGS -jar ofbiz.jar "$@"
	
	8. Step to Execute Opentaps
		$ ./startofbiz.sh




#debug in eclipse 
	please refer http://www.opensourcestrategies.com/ofbiz/ofbiz_eclipse.php

#IVY refresh dependency for spring 

	> got to folder where ivy.xml is.
	$ ant resolve

#WSDL TO JAVA
	#USED URLS
		1. https://www.encompassparts.com/soap/modelPartList.wsdl
		2. https://www.encompassparts.com/soap/modelSearch.wsdl

	#Process
	cd skulocity-opentaps/hot-deploy/restcomponent/src
	wsimport -keep -p com.encompassparts.soap.modelpart https://www.encompassparts.com/soap/modelPartList.wsdl
	-p for package name
	-keep to save file
#SOAP CONFIG
	see hot-deploy/restcomponent/config/application.properties file

#Protocol Configuration
	>Opentaps does not have all ssl protocols enabled so for enabling this please add this as argument in startofbiz.sh
	>PROTOCOLS_VERSION="-Dhttps.protocols=SSLv3,TLSv1,TLSv1.1,TLSv1.2"


=====================================================================================================================

postgres command:-
sudo -u postgres psql postgres
sudo psql -U postgres  -h skulocity-stage-postgres-rds.c8t2zusu80fc.us-east-1.rds.amazonaws.com skulocity  < skulocity.pgsql
\password :- This command is used to change the password of the user postgress

Ubuntu Command:-
to give permission:- sudo chmod -R 400 Skulocity-TestServer.pem

========================================================================================================================
Merge command:-
1)=>git pull origin skulocity-development
3)=>git stash
4)=>git checkout master
5)=>git pull origin master
6)=>git merge skulocity-development
7)=>git push origin master
8)=>git checkout skulocity-development
9)=>git stash pop

===========================================================================================================================
VPN Command:-sudo openvpn --config arun.ovpn
passphrase:  d3v3l0p5
============================================================================================================================
to create war:-


sudo nohup java -jar -Dserver.port=9090 Skulocity-Management-Console-0.0-SNAPSHOT.jar&

to restart nginx:-

sudo nginx -S reload

pg_dump -t table_to_copy source_db | psql target_db

COPY sample_table(field1,field2) FROM 'C:\tmp\sample_data.csv' DELIMITER ',' CSV HEADER;

==============================================================================================================================
to upload file from local to server:-

/Downloads/pem$ sftp -i Skulocity-MainServer.pem ubuntu@10.0.2.10
Connected to 10.0.2.10.
sftp> get production.sql
File "/home/ubuntu/production.sql" not found.
sftp> put production.sql 
Uploading production.sql to /home/ubuntu/production.sql
production.sql








