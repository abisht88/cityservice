# cityservice
How to build and execute the program in your local
  1) git clone https://github.com/abisht88/cityservice
  2) cd cityservice
  3) gradle build
  4) java -jar .\build\libs\cityservice-0.0.1-SNAPSHOT.jar


Sample Input:
C:\software\kafka\kafka_2.13-3.2.0\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic input_topic
>{"city":"BERLIN"}
>{"city":"London"}



Sample Output :
C:\software\kafka\kafka_2.13-3.2.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic output_topic
{"city":"BERLIN","country":"GERMANY","population":3645000}
{"city":"LONDON","country":"UK","population":9000000}


Following are the valid input city name for which service will push data to the output_topic:
BERLIN, LONDON, VIRGINIA, WARSAW,PUNE
