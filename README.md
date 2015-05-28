# Xadrez Java

Esse programa foi desenvolvido como trabalho da disciplina Orientação a Objetos do curso Engenharia de Software da Universidade de Brasília - Campus Gama.

## Noções básicas de funcionamento

Ao executar a aplicação Java, o jogo é automaticamente iniciado, sendo que o primeiro turno sempre é do jogador cujas peças são brancas.

O jogo somente aceitará que sejam clicadas nas peças do time dono do turno. Assim que uma peça for selecionada, seu quadrado ficará na cor verde e todos os possíveis caminhos que ela pode seguir serão destacados com a cor amarela. Para realizar um movimento válido, clique em um dos quadrados destacados. Para selecionar outra peça do mesmo time, basta clicar duas vezes sobre ela.

Sempre que um movimento válido é realizado, o estado dos quadrados são resetados para a cor normal e o turno passa para o próximo time.

Quando ocorrer um check, o jogador do time cujo Rei está em perigo é avisado, seu quadrado fica vermelho até que a situação não exista mais.

Quando o rei de qualquer um dos times é morto, as peças não podem mais se movimentar e o jogo deve ser reiniciado para que se possa jogar novamente.

## O Código

Esse repositório é um FORK de [link do repositório original](https://gitlab.com/monitoria_oo_2015_1/Chess).  
Na versão original, a interface gráfica já havia sido implementada e, apenas foram feitas modificações quanto a jogabilidade do xadrez.

### O Que Foi Adicionado

* Classe **Piece**  
	Na versão original as peças eram apenas um atributo String do objeto **Square** que continha o caminho para a imagem.  
	Na versão deste repositório, as peças são objetos distintos que possuem seus próprios atributos e métodos. Cada tipo diferente de peça é uma classe que herda da classe abstrata **Piece**; são elas **Pawn**, **Tower**, **Horse**, **Bishop**, **Queen** e **King**.  
	Cada uma dessas classes possui um método que retorna, de acordo com a posição e o tipo da peça, todas as posições para as quais essa peça naturalmente poderia se mover, sem levar em consideração as outras peças do jogo.

* Classe **Team**  
	Adicionalmente, observou-se a necessidade de se gerenciar de uma forma mais eficiente o conjunto de peças do mesmo time, criou-se, assim, uma classe **Team** que, na verdade, herda de **ArrayList<Piece>** por se comportar exatamente como uma lista de peças. Essa classe possui métodos, por exemplo, que verificam se alguma de suas peças ocupa uma determinada posição no tabuleiro.  
	Existe também uma classe chamada **TeamSetup** que inicializa as peças com suas respectivas imagens. Essa classe foi pensada para facilitar uma possível implementação de câmbio de estilo, isto é, quando houver a necessidade de alterar a imagem das peças no jogo, será preciso apenas modificar os métodos dessa classe.	

* Classe **GameControl**  
	Na versão original, havia apenas uma classe de controle a qual se chamava **SquareControl**. Essa classe era suficiente enquanto não havia uma lógica atuando para gerenciar o andamento do jogo e das jogadas. Porém, com a alteração dessa lógica de gerenciamento, o controle apenas dos quadrados não era mais capaz de conduzir o andamento do jogo e então, criou-se a classe **GameControl**.  
	Essa classe contém as duas instâncias da classe Team, ela é responsável por tudo que diz respeito ao controle do jogo. Por exemplo, ela limita a movimentação de cada peça de acordo com as outras peças distribuidas no tabuleiro, verifica se houve um checkmat, verifica se uma jogada é válida ou não, verifica se determinada peça pertence a um time ou a outro, etc.
	
* Classe **FalseMovementException**  
	O escopo do projeto pedia para que fosse implementada uma exceção para movimentos inválidos. Assim que um movimento válido é identificado, uma exceção **FalseMovementException** é levantada, uma mensagem é exibida no log e o jogo retorna para sua execução normal, o turno continua pertencendo ao jogador que errou o movimento e então ele pode selecionar outro caminho ou outra peça.



