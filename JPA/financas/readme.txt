Para criarmos um EntityManagerFactory precisamos de um persistence.xml criado dentro da pasta src/META-INF. 
É através do persistence-unit definido no persistence.xml que criamos um EntityManagerFactory 
através da chamada Persistence.createEntityManagerFactory() que recebe como parâmetro a unidade de persistência. 
Porém , isso não é suficiente. O responsável pela persistência é o EntityManager criado a partir da 
chamada do método EntityManagerFactory.createEntityManager. Para que um objeto seja sincronizado no banco, 
precisamos iniciar uma transação, inclusive comitá-la. Fazemos isso através da função 
EntityManager.getTransaction().begin() para iniciar a transação, EntityManager.persist() para persistirmos 
nosso objeto e EntityManager.getTransation().commit() para comitá-lorespectivamente. 
Por fim, não podemos nos esquecer de fechar a transação em seguida através do método EntityManager.close() .

Comandos sql

>drop database nome_database;
>create database nome_database;
>use nome_database;
>show tables;

Vantagens de se usar o NamedQuery:
As NamedQueries são lidas quando o JPA inicia, ou seja quando a aplicação sobe. 
Assim já percebemos qualquer erro de sintaxe no JPQL desde o início. 
Além disso, há um pequeno ganho no desempenho, pois as queries já foram analisadas.
Tente uma vez e gere um erro de sintaxe propositalmente. Abra a classe Movimentacao e 
crie um erro, por exemplo, escreva from movimentacao. Depois execute qualquer teste e 
repare que JPA já gera uma exceção ao subir.

Como desvantagem podemos mencionar que as queries poluem as entidades e dificultam a 
leitura e entendimento do DAO, já que o JPQL e a execução do mesmo estão separados.