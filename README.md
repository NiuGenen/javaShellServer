# javaShellServer
server for project: javaShell. 

# note
- It is not good to use external jar "sqljdbc42.jar" included in this project
- This jar is used to connect to sql server
- It is better to manage jar with maven 

```
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>6.1.0.jre8</version>
</dependency>
```

- But this jar seems to depend on a lot of jars and it takes me too much time to wait for its updating
- So I interupt it
- Then I want to start updating again, but it fails
- I don't know how to solve this problem so I will just use "sqljdbs42.jar"
