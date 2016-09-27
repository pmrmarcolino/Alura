Servidor :

$ java -cp hsqldb.jar org.hsqldb.server.Server --dbname.0 loja-virtual --database.0 file:loja-virtual

Editor visual do banco de dados: 

$ java -cp hsqldb.jar org.hsqldb.util.DatabaseManager

type: HSQL Database Engine Server
URL: jdbc:hsqldb:hsql://localhost/loja-virtual

Como funciona o retorno do método execute da interface Statement?
O método é claro em seu javadoc: se o retorno é um ResultSet por conta de um select, ele retorna true. Caso contrário ou 
caso não haja nenhum valor retornado, o método devolve false.

Quais os riscos de utilizar um Statement ao inves de um PreparedStatement? Você já ouviu falar ou conheceu sistemas que sofriam com tais problemas?
O principal problema de não usar statement está ligado com o SQL Injection: os usuários podem quebrar nossas queries e atacar nosso sistema caso eles escrevam valores específicos em nossos campos e não tratemos eles.
Outro problema é de performance: um prepared statement permite execução de inserts ou updates em batch.