# Sobre o projeto

Projeto que simula compras de albums.  Com camada de segurança, criptografia, autenticação, integração com spotify e documentação de APIs.

# Como buildar o projeto?

Todos as configurações de variáveis de ambiente já estão devidamente feitas. É necessário ter o JDK 17 ou superior e docker instalado na sua máquina junto com o pacote docker compose. 

Baixe os arquivos ou clone o repositório na pasta de sua preferência.
```git clone https://github.com/bc-fullstack-04/roberto-lucas-backend```.

É necessário fazer o install dos arquivos do maven. Pelo intelliJ, selecione o icone do maven do lado direito da IDE, expanda as pastas de ambos aplicativos, expanda a pasta lifecycle, selecione o icone de "proibído" e clique duas vezes em install. Faça o processo para ambas as pastas.

Na pasta onde o projeto está, abra o console/cmd e digite

```docker compose -f docker-compose.yml build``` 

para buildar o projeto e todos os containers relacionados.

Em seguida, entre com o código 

```docker compose -f docker-compose.yml run```

para rodar o projeto.

# Documentação das APIs

A documentação de ambas as aplicações estão disponíveis nos endereços a seguir. É necessário estar com o projeto rodando para conseguir acessar. Todos os endpoints, suas funções e parâmetros poderão ser encontrados por lá para saber como testar manualmente.

```
http://localhost:8081/api/swagger-ui/index.html#
http://localhost:8082/api/swagger-ui/index.html
```


# About the project
A project that simulates albums purchases. It comes with security layer, encryption, user authentication, Spotify integration and API documentation.

# How to build the project?

All environment variable settings have already been done. You must have JDK 17 or higher and docker installed on your machine along with the docker compose package. The maven ```clean install``` has already been done for both apps in the latest version of the code.

Download the files or clone the repository to your preferred folder.

```git clone https://github.com/bc-fullstack-04/roberto-lucas-backend```.

It is necessary to install the maven files. Using intelliJ, select the maven icon on the right side of the IDE, expand the folders of both applications, expand the lifecycle folder, select the "forbidden" icon and double click on install. Do the process for both folders.

In the folder where the project is, open console/cmd and type

```docker compose -f docker-compose.yml build```

to build the project and all related containers.

Then enter the code

# API documentation 

```docker compose -f docker-compose.yml run```

to run the project.

The documentation for both applications are available at the following addresses. You must have the project running to be able to access it. All endpoints, their functions and parameters can be found there to know how to manually test it.

```
http://localhost:8081/api/swagger-ui/index.html#
http://localhost:8082/api/swagger-ui/index.html
```

