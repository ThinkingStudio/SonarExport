# Pixolut Sonar Issue Exporter

This application help to export Issues from SonarQube service.

## Configuration

Open `resources/app.properties` file and type in your sonar configurations:

```properties
#sonar.host=http://sonar.mycomp.com
#sonar.login.token=MY_LOGIN_TOKEN
#sonar.project.key=MY_PROJECT_KEY
```

## Start the application

Start the application in dev mode

```
mvn clean compile exec:exec
```

Start the application in prod mode

```
mvn clean package
cd target/dist
unzip *
./start
```

Once application has been started, you should see something like:

```
  __   _                _     ___   __   __        _     _       _    _    _   ___   _   _  
 (_   / \  |\ |   /\   |_)     |   (_   (_   | |  |_    |_  \/  |_)  / \  |_)   |   |_  |_) 
 __)  \_/  | \|  /--\  | \    _|_  __)  __)  |_|  |_    |_  /\  |    \_/  | \   |   |_  | \ 
                                                                                            
                                                       powered by ActFramework r1.4.13-282a3

 version: r1.0.0-${buildNumber}
scan pkg: com.pixolut.sonar
base dir: /home/luog/p/pixolut/SonarExport
     pid: 11865
 profile: dev
    mode: DEV

     zen: Explicit is better than implicit.

22:28:49.476 [main] INFO  a.Act - loading application(s) ...
22:28:49.484 [main] INFO  a.a.App - App starting ....
22:28:49.592 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
22:28:49.635 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
22:28:50.670 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
22:28:50.670 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
22:28:50.915 [main] INFO  a.a.App - App[Sonar Issue Exporter] loaded in 1431ms
22:28:50.921 [jobs-thread-3] INFO  a.Act - start compiling API book
22:28:50.941 [main] INFO  o.xnio - XNIO version 3.3.8.Final
22:28:50.966 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.8.Final
22:28:51.130 [main] INFO  a.Act - network client hooked on port: 5460
22:28:51.132 [main] INFO  a.Act - CLI server started on port: 5461
22:28:51.133 [main] INFO  a.Act - it takes 2933ms to start the app
```

Now you can open browser at `http://localhost:5460` to view the home page and click on `download` button

**Tips** You can also run the app in your IDE by run the main entry class `Report`

