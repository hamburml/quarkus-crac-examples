### Anwendung starten

- Endpunkt cat aufrufen
- Log ca 700 ms
- danach nochmal, 100 ms
- init von mp rest dauert ca 600 ms

```
2024-07-23 20:06:55,917 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat start 2024-07-23T18:06:55.917653105Z
2024-07-23 20:06:55,931 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getRandomFact start 2024-07-23T18:06:55.931827614Z
2024-07-23 20:06:56,628 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat after 2024-07-23T18:06:56.628444428Z
2024-07-23 20:06:56,628 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat duration 696
2024-07-23 20:06:56,629 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat after 2024-07-23T18:06:56.629223112Z
2024-07-23 20:06:56,629 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat duration 711
2024-07-23 20:07:08,925 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat start 2024-07-23T18:07:08.925620468Z
2024-07-23 20:07:08,926 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getRandomFact start 2024-07-23T18:07:08.926038760Z
2024-07-23 20:07:09,042 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat after 2024-07-23T18:07:09.042054766Z
2024-07-23 20:07:09,042 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat duration 116
2024-07-23 20:07:09,042 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat after 2024-07-23T18:07:09.042643991Z
2024-07-23 20:07:09,042 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat duration 117
```

Mit startup event und request dahin...
```
2024-07-23 20:11:03,651 INFO  [de.ham.cra.ser.CatService] (Quarkus Main Thread) StartupEvent: {"data":["Both humans and cats have identical regions in the brain responsible for emotion."]}
2024-07-23 20:11:03,670 INFO  [io.quarkus] (Quarkus Main Thread) crac1 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.12.3) started in 1.662s. Listening on: http://localhost:8080
2024-07-23 20:11:03,670 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-07-23 20:11:03,671 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, rest-client, rest-client-jackson, rest-jackson, smallrye-context-propagation, smallrye-openapi, swagger-ui, vertx]
2024-07-23 20:11:16,940 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat start 2024-07-23T18:11:16.940703214Z
2024-07-23 20:11:16,941 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getRandomFact start 2024-07-23T18:11:16.941026676Z
2024-07-23 20:11:17,096 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat after 2024-07-23T18:11:17.096214303Z
2024-07-23 20:11:17,096 INFO  [de.ham.cra.ser.CatService] (executor-thread-1) getCat duration 155
2024-07-23 20:11:17,096 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat after 2024-07-23T18:11:17.096897045Z
2024-07-23 20:11:17,097 INFO  [de.ham.cra.res.CatResource] (executor-thread-1) getCat duration 156
```

### Anwendung starten

/quarkus-crac-examples/crac1/target/quarkus-app$ java -XX:CRaCCheckpointTo=cr -jar quarkus-run.jar

### im zweiten Terminal

jcmd quarkus-run.jar JDK.checkpoint

### Fehler

....
(00.003985) Unlock network
(00.003990) Unfreezing tasks into 1
(00.003995) 	Unseizing 15763 into 1
(00.004001) Error (compel/src/lib/infect.c:358): Unable to detach from 15763: No such process
(00.004009) Error (criu/cr-dump.c:2063): Dumping FAILED.

Laut Kommentar in https://foojay.io/today/springboot-3-2-crac/ fehlen Zugriffsrechte auf criu, hab chmod 777, geht nicht. 