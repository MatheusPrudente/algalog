<h2 align ="center">
  📮 Algalog
</h2>

<p align="center">
    <img src="https://img.shields.io/github/languages/count/MatheusPrudente/algalog"/>
    <img src="https://img.shields.io/github/repo-size/MatheusPrudente/algalog"/>
    <img src="https://img.shields.io/github/last-commit/MatheusPrudente/algalog"/>
    <img src="https://img.shields.io/github/issues/MatheusPrudente/algalog"/>
</p>


## 	:rocket: Tecnologias 

Este projeto foi desenvolvido com as seguintes tecnologias : 

- Java
- Spring Boot

## :desktop_computer: Projeto

  Projeto foi desenvolvido durante o curso *__Mergulho Spring Rest__* oferecida pela [AlgaWorks](https://www.algaworks.com). O projeto consiste em uma Api Rest de Gerenciamento de Entregas

## :books: Diagrama de Classes

![diagrama-de-classes](https://user-images.githubusercontent.com/80559882/188333831-34eb6032-2623-48c9-835f-ea4b21b0c891.png)

## :bookmark_tabs: Documentação da API

#### URL base

```https
  http://localhost:8080/algalog/
```

#### Obter Listagem dos Clientes Cadastrados

```https
  GET /clientes
```

#### Buscar Cliente Cadastrado Pelo Id

```https
  GET /clientes/{clienteId}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `clienteId` | `Long` | **Obrigatório**. Id do Cliente |

#### Adicionar um Cliente

```https
  POST /clientes/
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `clienteInput` | `ClienteInput` | **Obrigatório**. Informações do Cliente adicionado |

#### Atualizar um Cliente

```https
  PUT /clientes/{clienteId}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `clienteId` | `Long` | **Obrigatório**. Id do Cliente |
| `clienteInput` | `ClienteInput` | **Obrigatório**. Informações do Cliente adicionado |

#### Deletar um Cliente

```https
  DELETE /clientes/
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `clienteId` | `Long` | **Obrigatório**. Id do Cliente |

## :pushpin: Rodando localmente

#### Banco H2 

```
  http://localhost:8080/algalog/h2-console
```

#### SpringDoc

```
  http://localhost:8080/algalog/swagger-ui/index.html#/
```

## :crossed_swords: Desafios

- [X] Criar Model para as Classe Cliente
- [X] Criar Input para as Classe Cliente
- [X] Criar Assembler para as Classe Cliente
- [X] Substituir cliente pelo ClienteModel e ClienteInput 
- [X] Inserir SpringDoc

