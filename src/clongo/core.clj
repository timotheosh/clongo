(ns clongo.core
  (import [java.util
           ArrayList]
          [com.mongodb
           MongoClient
           MongoClientOptions
           MongoClientOptions$Builder
           ServerAddress
           ReplicaSetStatus])
  (:gen-class))

(def mongo-hosts ["example-mongodb-rs1-1.us-east-1.inindca.com:27017"
                  "example-mongodb-rs1-2.us-east-1.inindca.com:27017"
                  "example-mongodb-rs1-3.us-east-1.inindca.com:27017"
                  "example-mongodb-rs1-backup-1.us-east-1.inindca.com:27017"
                  "exampledb-rs1-1.us-east-1.inindca.com:27019"])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; MongoClientURI("mongodb://localhost/?ssl=true")
  (let [options (MongoClientOptions$Builder.)
        addr (ArrayList.)]
    (.sslEnabled options false)
    (doseq [host mongo-hosts]
      (.add addr (ServerAddress. host)))
    (let [mclient (MongoClient. addr (.build options))]
      (for [x (.listDatabaseNames mclient)]
        (println x))
      (let [status (.getReplicaSetStatus mclient)]
        (println (.toString status))))))
