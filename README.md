# Abstract


![image-illustration](doc/illustration-readme.jpg)


HEIG-DAI_PW1 is a CLI application developed by two students as part of the practical work number 1 for the DAI course at HEIG-VD.

Its purpose is to process images from a supported file format. It currently offers two type of processing:

- Color inversion: 
  - inverts the RGB values of each pixel.
- Black and white conversion: 
  - produces a black-and-white picture, with a choice of gradient (luminosity) made by user


>Supported formats:
>For now, the following image format is supported:
>- BMP

**Authors:**
- Noé Léderrey — [@noelebosss](https://github.com/noelebosss)
- Enis Sadiku — [@ens-coder](https://github.com/ens-coder)



# HEIG-DAI_PW1
HEIG-DAI_PW1 is designed to be used easily. You just need to have a JRE (at leat version 25.04
another version is not officialy supported).

The main structure of using software is:

`java -jar nameOfjar.jar nameOfcommand -optionIfAvaible -i filePathOriginalPicture -o destinationProcessedPicture `

The tree of pictures is:
```
img\
    |
    |___\original
    |
    |___\processed
```

- original have the non-processed pictures
- processed contain the processed pictures of original


**Example:**

For example, if you want to apply grey filter at 30% luminosity on souris.bmp picture:

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar gradient -p 30 -i img/original/souris.bmp -o img/processed/souris_gradient_30.bmp
```
You can also leave the gradient as it is by default (100%)
```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar gradient -i img/original/souris.bmp -o img/processed/souris_gradient_100.bmp
```

> **note:** use the SNAPSHOT version jar because it will garanty you to have all the dependances
> in the jar

## Configuration - build
To use properly the software you need to configure correctly the environement. You need to follow
theses steps:

>**note:** we assume you that you have the correct configuration 
> with Git and stuffs - the tutorial has been tested on WSL Ubuntu and Windows 11

**1) clone the repo from GitHub:**


```sh
git clone https://github.com/ens-coder/HEIG-DAI_PW1.git
```
```sh
cd HEIG-DAI_PW1
```


**2) Maven:**

The project has been designed with Maven that means we strongly recommand you to follow this solution.

You need to generate environnement directly compatible with the software. First you have to tools 
to use depends on your OS:
- Windows:  `mvnw.cmd`
- Linux:  `mvnw`

> **note:** Maven may be download some requirements


**2.1) dev mode**

If you want, you can use another option for enhance project (DEV):

Package the project:
- Windows:  `.\mvnw.cmd package`
- Linux:  `./mvnw package`

Clean file of target package:
- Windows:  `.\mvnw.cmd clean`
- Linux:  `./mvnw clean`

Test:
- Windows:  `.\mvnw.cmd test`
- Linux:  `./mvnw test`



**2.2) dependency of Maven**

The project contain theses dependencies:
- PICOCLI (interface)
- Junit (to do unit test)


**3) generate project and environnement**:

You need to open a command line (cmd or powershell on Windows and Terminal in linux):

- `.\mvnw.cmd clean package` (Windows)
- `./mvnw clean package` (Linux)

![maven-3](doc/maven-1.png)


## Outputs

### Help

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar --help
```
```text
Usage: HEIG-DAI_PW1-1.0-SNAPSHOT.jar [-hV] [COMMAND]
Process an input image and write the result to an output image.
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  invert    Inverts the colours of the input image (negative effect).
  gradient  Converts the input image towards black and white.
  help      Display help information about the specified command.
```

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar gradient --help
```
```text
Usage: HEIG-DAI_PW1-1.0-SNAPSHOT.jar gradient [-hV] -i=<inputFile>
       -o=<outputFile> [-p=<percentage>]
Converts the input image towards black and white.
The percentage sets how strong the luminosity is:
  0 is no luminosity , 100 gives a full luminosity,
  -h, --help                Show this help message and exit.
  -i, --input=<inputFile>   The input image file.
  -o, --output=<outputFile> The output image file.
  -p, --percentage=<percentage>
                            The intensity of the gradient, from 0 to 100.
                              Default: 100
  -V, --version             Print version information and exit.
```

### Processing

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar invert -i img/original/souris.bmp -o img/processed/souris_invert.bmp
```
```text
invert: img/original/souris.bmp -> img/processed/souris_invert.bmp
```

<br>

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar gradient -p 30 -i img/original/souris.bmp -o img/processed/souris_gradient_30.bmp
```
```text
gradient 30%: img/original/souris.bmp -> img/processed/souris_gradient_30.bmp
```

| original | invert | gradient 30% |
|---|---|---|
| ![original](img/original/souris.bmp) | ![invert](img/processed/souris_invert.bmp) | ![gradient 30%](img/processed/souris_gradient_30.bmp) |


### Errors

* input file cannot be read (not a BMP picture):

```sh
 java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar invert -i img/original/chien.jpg -o img/processed/chien-invert.bmp
```
```text
Error: cannot read 'img/original/chien.jpg': the file is not a BMP image
```

* input file cannot be read (it doesn't exist):

```sh
java -jar target/HEIG-DAI_PW1-1.0-SNAPSHOT.jar invert -i img/existe_pas.bmp -o img/processed/out.bmp
```
```text
Error: cannot read 'img/existe_pas.bmp': img/existe_pas.bmp (No such file or directory)
```


# Sources
- DAI: courses
- https://en.wikipedia.org/wiki/BMP_file_format, coupled with AI for understanding how "decrypt" a 24-BMP images
- PRG2: laboratory

# Related link for the project:
- [PW1 - discussion](https://github.com/orgs/heigvd-dai-26/discussions/5)
- [PW1 - requierements](https://heigvd-dai-26.github.io/practical-work/pw1.html)
