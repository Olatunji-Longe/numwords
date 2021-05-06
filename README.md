### About

---

This is an application that accepts a number and prints it as words - with capability to handle both 
positive and negative numbers (including whole numbers and decimals)

##### Processing Range 
- minimum long number `-9223372036854775807`
- maximum long number `9223372036854775807`

| Sample input     | Sample output     |
| :------------- | :----------: |
|  0    | Zero   |
|  13   | Thirteen |
|  107  | One hundred and seven   |
| 103567294   | One hundred and three million five hundred and sixty seven thousand two hundred and ninety four  |
|  -48006  | Negative forty eight thousand and six  |

### Requirements

---

- Java 8
- Terminal

### How to build

---

Carry out the following steps from a Terminal to build the project

- Change directory to the project root
    ```
    cd numwords
    ```

- From the project root, run the following command
    ```
    ./gradlew clean build
    ```

- A jar file named `numwords.jar` will be generated into the `numwords/build/libs` directory.


### Testing
Run the following command to execute and view unit test results
```
./gradlew clean test
```


### Executing The Program using the generated (Jar File)

---

***Flags***: The program uses one REQUIRED flag

|       Flag       |     Usage    | Description     | Default Value    |
| :-------------   | :----------: | :----------:    | :----------:    |
|  `-n` or `--num` |   required   | the number to be translated to words   | none (provided by user at runtime)  |


### How to Run the program

---

Copy the generated jar file to any preferred location and from that location, run a command patterned as follows;

Examples:

- print 2048 in words
  ```
  java -jar numwords.jar -n=2067
  ```
  
  OR
  
  ```
  java -jar numwords.jar --num=2067
  ```

- print -4096 in words
  ```
  java -jar numwords.jar -n=-4096
  ```
  
  OR

  ```
  java -jar numwords.jar --num=-4096
  ```

### For more program command help
```
java -jar numwords.jar -h
```

