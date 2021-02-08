# spring-scaffold

## Objetivo

Projeto Java com o objetivo de facilitar a criaÃ§Ã£o das clesses controller service e repository baseado em um domain

## Build

```
mvn clean package
```



## Parâmetros

- basePath : diretório do projeto 
- basePackage : pacote principal do projeto
- idType : classe do Id da tabela representada pelo domínio
- model : classe que reprensenta a tabela

## Executando o projeto
```
java -jar scaffold-1.0.0.jar basePath <Diretório do projeto> basePackage <pacote principal do projeto> idType <tipo de dado do Id da tabela> model <nome da classe de domínio>
```
### Exemplo
```
java -jar scaffold-1.0.0.jar basePath /home/usuario/worksapace/projetoTest basePa
ckage org.projetoteste idType Long model ClasseTeste
```
