# NORDNET JAVA - zabuza-Project

## I. INFORMATION


## II. RELEASE CHANGES


		
## III. RELEASE PACKAGE

## IV. DEPLOYMENT
	
### 4.1. PRE-REQUISES:
	- JDK_1.0.7-79
	- apache-tomcate-7.56 
	- MySql-5.1	
	
### 4.2. DEPLOYMENT INSTRUCTIONS
#### 4.2.1 TOMCAT CONFIGURATION
	- tomcat/conf/server.xml: add new resources
			<Resource type="javax.sql.DataSource" auth="Container" name="jdbc/AppWatcherDS"
              maxWait="5000" minIdle="2" maxIdle="30" maxActive="150"
              username="test" password="test"
              url="jdbc:mysql://localhost:3306/appwatcher_qualif"
              driverClassName="com.mysql.jdbc.Driver" validationQuery="SELECT 1" validationQueryTimeOut="1" testOnBorrow="true"/>
			 
	- tomcat/conf/context.xml: 	add new resouce link
			<ResourceLink name="jdbc/AppWatcherDS" global="jdbc/AppWatcherDS" auth="Container" type="javax.sql.DataSource" />
#### 4.2.2 DEPLOYMENT BY BAMBOO's JOB


## V. RUN TEST BY REST CLIENT

