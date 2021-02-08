# spring-scaffold

## Objetivo

Projeto Java com o objetivo de facilitar a criação das clesses controller service e repository baseado em um domain

## Build

```
mvn clean package
```



## Par�metros

- basePath : diret�rio do projeto 
- basePackage : pacote principal do projeto
- idType : classe do Id da tabela representada pelo dom�nio
- model : classe que reprensenta a tabela

## Executando o projeto
```
java -jar scaffold-1.0.0.jar basePath <Diret�rio do projeto> basePackage <pacote principal do projeto> idType <tipo de dado do Id da tabela> model <nome da classe de dom�nio>
```
### Exemplo
```
java -jar scaffold-1.0.0.jar basePath /home/usuario/worksapace/projetoTest basePa
ckage org.projetoteste idType Long model ClasseTeste
```
