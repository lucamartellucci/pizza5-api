# pizza5-api
Api REST per menu pizze e relativi ingredienti

---

### Prerequisiti
- [JDK 8]
- [Maven 3.x.x]
- [Git]
- [MySql 5.x.x]
- [Tomcat 8.x.x]

### Git setup
Dopo aver clonato il repository git, eseguite i seguenti comandi (dalla cartella del progetto) per personalizzare l'utente con cui effettuate le commit:
```sh
$ git clone https://github.com/AdvancedBusinessSolutions/pizza5-api.git
$ cd pizza5-api
$ git config user.email "your-name@absontheweb.com"
$ git config user.name "your-name"
```

### Build
Dal propmt della shell eseguire il comando:
```sh
$ mvn clean package
```
la build produrrà il file **pizza5-api.war** che di default sarà depositato nella cartella **./deploy**. 
E' possibile modificare la cartella di deploy tramite un opzione maven:

```sh
$ mvn clean package "-Dcontainer.deploy.dir=/opt/apache-tomcat-8.0.32/webapps"
```

La build da eclipse ovviamente sarà con i soli goal più l'eventuale opzione per specificare la directory di deploy:
```
clean package "-Dcontainer.deploy.dir=/opt/apache-tomcat-8.0.32/webapps"
```

### MySql Setup
Eseguire lo script sql presente nella directory del progetto **src/env_config/db.sql**. Dal prompt della shell usate il comando:
```sh
$ mysql -uroot -p < src/env_config/db.sql
```

### Tomcat Setup
Per effettuare il setup di tomcat eseguire i seguenti passi:
* Scaricare [Connector/J] e installarlo nella cartella TOMCAT_HOME/lib
* Modificare il file TOMCAT_HOME//conf/context.xml e aggiungere la seguente configurazione all'interno del tag Context: 
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
***Nota***: al momento la configurazione prevede l'utilizzo dell'utente MySql root configurato con password root.

[Connector/J]: <https://dev.mysql.com/downloads/connector/j/>
[JDK 8]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
[Maven 3.x.x]: <https://maven.apache.org/download.cgi>
[Git]: <https://git-scm.com/downloads>
[MySql 5.x.x]: <http://dev.mysql.com/downloads/>
[Tomcat 8.x.x]: <https://tomcat.apache.org/download-80.cgi>


