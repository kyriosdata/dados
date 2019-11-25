[![Actions Status](https://github.com/kyriosdata/dados/workflows/dados/badge.svg)](https://github.com/kyriosdata/dados/actions)

# dados

Biblioteca para geração de dados para teste: _simples de usar_, _leve_ e _eficiente_.

Esta biblioteca faz uso de uma estratégia baseada em fases: (a) léxica; (b) sintática e (c) semântica.

### Requisitos versão léxica

1. Na fase léxica, o gerador simplesmente produz, conforme o tipo de interesse, um valor "arbitrário" correspondente.

1. Alguns valores devem ser obtidos de domínios bem-definidos. Por exemplo, se um determinado atributo deve armazenar uma unidade de medida, então é desejável sortear uma unidade de medida dentre aquelas disponíveis, em vez de fazer uso de uma sequência qualquer de caracteres.
1. Ainda há cenários mais elaborados, por exemplo, endereço. Nesse caso, o logradouro e o número, além da cidade e do estado, deverão estar em conformidade com o CEP.

### Links relevantes

- [Unidades de medida](http://unitsofmeasure.org/ucum.html)

/\*Andoly

<li>Baseando-se no artigo “Survey on Automated Test Data Generation ” a partir do objetivo de entender métodos para geração de dados de nível léxico.
O esquema automatizado de geração de dados de teste gera um conjunto de dados de teste automaticamente com base em critérios de adequação. Em que o principal objetivo é resumir as principais técnicas no reconhecimento da automatização teste a geração de dados.

As técnicas de geração de dados para teste são:
Dados de teste aleatório: geração é usada para fazer o teste de entrada aleatoriamente até que a entrada útil seja encontrada
Dados de teste orientados a objetivos: Melhor que o método anterior, pois o estágio de seleção dos caminhos para obter a entrada correta, é eliminada
Teste orientado para o caminho: Visa detecção de erros a partir da comparação mais criteriosa dos dados de teste. Essa metodologia divide três partes:

<ul>
<li>Analisador de programa
<li>Seletor de caminho 
<li>Dado de teste geração
<ul>
  
Técnicas de geração de dados automatizado: 
<ul> 
<li> Algoritmo Genetico: Os casos de teste são descritos por cromossomos, que consistem em informações sobre o componente a ser produzido, métodos a serem invocados e valores para ser passado como entrada. GA usou três operadores que são: seleção, crossover e mutação. Usando algum método como operação seleção para obter o cromossomo. Realiza assim várias iterações gerando novos indivíduos melhor adaptados

<li> Organização por enxame de partículas (PSO): é motivado pela simulação de comportamentos de predação de aves. Em que uma série de partículas que constituem um enxame em movimento em torno do espaço de busca procurando a solução ideal
  
<li>Otimização de colunas de formigas (ACO):  traz informações relacionadas ao comportamento de forrageamento de formigas reais visando um caminho entre sua colônia e fonte de alimento
  
<li>Algoritmo Genético do Sistema de Colônia Ant (ACSGA): Os dados do teste são gerados usando o conceito misto de ambos o algoritmo(ACO E AG), em que a técnica visa superar a limitação de ACO
  
<li>Algoritmo Genético Enrolado de Partículas Otimização (GAPSO): é a combinação de enxames genéticos e de partículas, em que o PSO tem o capacidade realizar pesquisa local e convergência rápidas, portanto trata a deficiências da GA
<p>  
*/
