# cadastro-livros-TJJUD

## Inicialização do projeto no Spring Initializr

![image](https://github.com/user-attachments/assets/94f5add1-cdf0-47d8-ad03-c26c92962f01)

### Executando o sistema (em localhost)

Para executar a aplicação desenvolvida neste repositório, acesse a pasta:

```
cadastro-livros-TJJUD\biblioteca\target
```

Execute o Jar File:

```
java -jar biblioteca-0.0.1-SNAPSHOT.jar
```

## Criação de View necessária para relatório

### Acesse o h2-console

```
http://localhost:8080/h2-console/
```

Utilize as credenciais definidas no application.properties para logar na interface do H2. Por padrão são:

```
spring.datasource.url=jdbc:h2:file:~/test3db
spring.datasource.username=root
spring.datasource.password=admin
```

### Crie a View vw_autor_livro_assunto

```
CREATE VIEW vw_autor_livro_assunto AS
SELECT 
    a.nome AS autorNome,
    l.titulo AS livroTitulo,
    l.editora AS livroEditora,
    l.edicao AS livroEdicao,
    l.ano_publicacao AS livroAnoPublicacao,
    l.valor AS livroValor,
    s.descricao AS assuntoDescricao
FROM 
    livro_autor la
JOIN 
    livro l ON la.livro_codL = l.codL
JOIN 
    autor a ON la.autor_cod_Au = a.cod_Au
LEFT JOIN 
    livro_assunto las ON las.livro_codL = l.codL
LEFT JOIN 
    assunto s ON las.assunto_cod_As = s.cod_As
ORDER BY 
    a.nome, l.titulo;
```
