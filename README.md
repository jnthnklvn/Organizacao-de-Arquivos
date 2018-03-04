# FileOrganizationED2
## Implementação e Comparação de Métodos de Organização de Arquivos
### Parte 1: Implementação do Método de Sequencial
  * O objetivo desta parte é usar a API Java NIO para implementar o método sequencial de organização para um arquivo de dados de alunos chamado "alunos.db". Os registros deste arquivo são de tamanho fixo e devem estar sempre na ordem das matrículas dos alunos. As operações a serem implementadas na manipulação deste arquivo são:

  * A. Inserir aluno: dada a instância de um aluno, a operação insere os seus dados no arquivo de forma que este permaneça ordenado pelo campo matrícula

  * B. Remover aluno: dada a matrícula de um aluno, a operação localiza e remove o registro correspondente a esse aluno do arquivo liberando o espaço alocado pelo registro correspondente.

  * C. Consultar aluno: dada a matrícula de um aluno, a operação localiza e devolve uma instância do aluno correspondente Na implementação destas operações, deve partir da seguinte interface:

### Parte 2: Implementação do Método de Brent

   * Segundo o site de notícias "G1", 7.603.290 alunos se inscreveram no Exame Nacional de Cursos (Enem) 2017. Para que volumes de dados dessa proporção possam ser armazenados e acessados de forma eficiente em aqruivos, faz-se necessário o uso de técnicas especializadas de organização de dados em arquivos.

   * O objetivo deste trabalho é medir e comparar o desempenho de técnicas de organização de arquivos quando submetidos a operações de armazenamento e acesso a grandes volumes de dados. As técnicas de organização que deverão ser avaliadas são:
    Sequencial
    Método de Brent

 * Tarefa A. Implementar o método de Brent.  Para isso, cada grupo deve usar a interface IFileOrganizer
A partir dessa inteface, deve-se implementar o método de Brent a partir de uma classe chamada OrganizadorBrent.

   * A. A função de incremento do método de Brent deve ser: Inc(chave) = (chave mod (P-2)) + 1, onde P é um valor primo que corresponde ao tamanho da tabela.

   * B. O tamanho da tabela (P) deve ser de 10.000.019 registros. Esse será o número máximo de registros que o arquivo conseguirá armazenar.

   * C. O nome do arquivo sobre o qual a implementação do método Brent deve manipular é "enem_brent.db" (disponibilizado pelo professor). Na prática, esse arquivo disponibilizado é composto por 10.000.019 "registros vazios" de alunos com 300 bytes cada. Os registros de aluno foram projetado com os seguintes campos de tamanho fixo:
    
```
      long matric; // 8 bytes
      String nome; // 80 bytes
      String endereco; // 100 bytes
      String telefone; // 20 bytes
      short curso; // 2 bytes
      String email; // 90 bytes 
```

 * Tarefa B: Migrar dados para o arquivo "enem_brent.db" (arquivo disponibilizado pelo professor)

   * Esta tarefa consiste em migrar dados de   7.603.290 alunos de um arquivo de dados aleatórios chamado enem_aleat.db para o arquivo enem_brent.db. O arquivo enem_aleat.db é disponibilizado pelo professor. Para isso, deve ser implementado um programa de migração que lê sequencialmente cada registro do arquivo enem_aleat.db e o insere no arquivo enem_brent.db. Esta inserção deve consistir em chamar a operação addReg da implementação do método de Brent (OrganizadorBrent).

### Parte 3: Análise de Desempenho
 * Esta tarefa consiste em comparar o desempenho de acesso entre o método de organização sequencial (implementado na terefa do Organizador Sequencial) e o método de Brent implementado na terefa anterior. Para isso, deve ser usado um arquivo com 1000 matrículas chamado selected.db (fornecido pelo professor). Essas 1000 matrículas foram coletadas alteatoriamente a partir do arquivo enem_aleat.db descrito na tarefa anterior. Como cada matrícula possui 8 bytes (tipo long em java), o tamanho total desse arquivo é de exatamente 8000 bytes (= 8x1000).  Nesta análise, deve-se:

 * A. Calcular o tempo total que o método sequencial leva para acessar os 1000 alunos indicados. O arquivo com os dados de todos os  7.603.290  alunos já organizados sequencialmente é chamado de enem_seq.db e é fornecido pelo professor. É a partir deste aquivo que o método sequencial deve acessar cada um dos 1000 alunos.

 * B. Calcular o tempo total que o método de Brent leva para acessar os mesmos 1000 alunos indicados. O arquivo a partir do qual essas 1000 consultas serão realizadas é o enem_brent.db depois do processo de migração especificado acima.

 * C. Criar um relatório com uma tabela comparativa que mostre o tempo total dispendido por cada método para localizar os 1000 registros indicados. Discutir no relatório o porquê dos resultados que forma obtidos.

 * Acesso aos arquivos (professor): https://drive.google.com/file/d/15ILkyaLaIQhETJhyMutsWMpcIiszT2tc/view?usp=sharing
 * Acesso aos arquivos (resultados): https://drive.google.com/file/d/1fL7vjg4Fhsg0C3AKKDH5aRU5JPkOyvK2/view?usp=sharing
