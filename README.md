[![Build Status](https://travis-ci.org/AdvancedBusinessSolutions/pizza5-api.svg?branch=master)](https://travis-ci.org/AdvancedBusinessSolutions/pizza5-api)
[![Coverage Status](https://coveralls.io/repos/github/AdvancedBusinessSolutions/pizza5-api/badge.svg?branch=master&service=github)](https://coveralls.io/github/AdvancedBusinessSolutions/pizza5-api?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/7dd4531f5d13418cb5a5c2b54890f27a)](https://www.codacy.com/app/gianluca-aiello/pizza5-api)

# pizza5-api
Api REST per menu pizze e relativi ingredienti



### Prerequisiti
- [JDK 8]
- [Maven 3.x.x]
- [Git]
- [MySql 5.x.x]
- [Tomcat 8.x.x]

### Git setup
Per configurare correttamente il vostro repo git locale eseguite i seguenti comandi:
```sh
$ git clone https://github.com/AdvancedBusinessSolutions/pizza5-api.git
$ cd pizza5-api
$ git config --global push.default simple
$ git config user.email "your-name@absontheweb.com"
$ git config user.name "your-name"
```

### Build
Dal prompt della shell eseguire il comando:
```sh
$ mvn clean package
```
la build produrrà il file **pizza5-api.war** che di default sarà depositato nella cartella **./deploy**. 
E' possibile modificare la cartella di deploy specificando la proprietà maven:

```sh
$ mvn clean package "-Dcontainer.deploy.dir=/opt/apache-tomcat-8.0.32/webapps"
```

La build da eclipse ovviamente sarà con i soli goal più l'eventuale opzione per specificare la directory di deploy:
```
clean package "-Dcontainer.deploy.dir=/opt/apache-tomcat-8.0.32/webapps"
```

### MySql Setup
Eseguire lo script sql **src/env_config/db.sql** presente nel progetto. Dal prompt della shell è possibile usare il comando:
```sh
$ mysql -uroot -p < src/env_config/db.sql
```

### Tomcat Setup
Per effettuare il setup di Tomcat eseguire i seguenti passi:
* Scaricare [Connector/J] e copiarlo nella cartella TOMCAT_HOME/lib
* Configurare il datasource per MySql modificando il file TOMCAT_HOME/conf/context.xml e aggiungere il seguente snippet xml all'interno del tag <Context>: 
```xml
<Resource name="jdbc/tomcatDataSource" auth="Container" type="javax.sql.DataSource"
         username="root"
         password="root"
         driverClassName="com.mysql.jdbc.Driver"
         url="jdbc:mysql://localhost:3306/pizza5?useUnicode=true&amp;characterEncoding=utf8"
         maxTotal="25"
         maxIdle="10"
         defaultTransactionIsolation="READ_COMMITTED"
         validationQuery="Select 1" />
```
***Nota***: al momento la configurazione prevede l'utilizzo dell'utente root di MySql configurato con password root. Eventualmente modificare la configurazione del datasource. 

### Test 
Una volta effettuata la build e dopo aver avviato Tomcat, verificate che l'applicazione funzioni correttamente navigando al seguente URL: 

<http://localhost:8080/pizza5-api/api/menu>


[Connector/J]: <https://dev.mysql.com/downloads/connector/j/>
[JDK 8]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
[Maven 3.x.x]: <https://maven.apache.org/download.cgi>
[Git]: <https://git-scm.com/downloads>
[MySql 5.x.x]: <http://dev.mysql.com/downloads/>
[Tomcat 8.x.x]: <https://tomcat.apache.org/download-80.cgi>


