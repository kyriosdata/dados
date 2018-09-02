## BASES NAO PROCESSADAS
As bases abaixo foram obtidas do [Portal Brasileiro de Dados Abertos](http://dados.gov.br/). Em consequência, conforme o Anexo I da Resolução número 2 de 24/03/2017 ([aqui](http://wiki.dados.gov.br/GetFile.aspx?File=%2fComiteGestor%2fResolu%C3%A7%C3%B5es%2fresolucao-cginda-2-24-3-2017%2cpdf.pdf)), podem ser usadas para o presente propósito deste projeto. 

- **ubs.csv** arquivo csv que identifica as dezenas de milhares de unidades básicas de saúde no páis.
- **codigos-nacionais.csv** contém códigos dos municípios do Brasil, 5570, além do estado correspondente.
- Filiados a partidos políticos. No portal dos dados abertos há extensa lista dos brasileiros filiados a partidos políticos, o que inclui o nome, o município, endereço, bairro, telefone. Por exemplo, só para o estado do ACRE, os filiados ao DEM superam os 4300 brasileiros. Após tratados é possível atingir milhões de nomes e de endereços, bem como telefones sintaticamente válidos por município. Dada a extensão deste conjunto de dados, sugere-se uso direto a partir do portal brasileiro de dados abertos ([aqui](http://dados.gov.br/dataset/filiados-partidos-politicos)).
- **gitNome e gitSobrenome** são respectivamente listas compostas por nomes e sobrenomes no formato CSV, extraindo de dados.gov.br, em Lista de Eleitores Filiados aos Partidos Políticos.
- **Codigo_Nacional_Atualizado.csv** contém códigos dos 5570 municípios do Brasil, com o estado correspondente, município, Código do unicípio;Código nacional e faixa com início e fim do CEP correspondente a cada município.

## Outros dados
- Conforme descrito em [CEP](https://pt.wikipedia.org/wiki/C%C3%B3digo_de_Endere%C3%A7amento_Postal), pode-se gerar CEP "válido" por estado, o que acredito ser suficiente para as nossas pretensões. Ou seja, não é necessário obter uma base de CEPs. 
- Conforme descrito em [título eleitor](https://pt.wikipedia.org/wiki/T%C3%ADtulo_eleitoral) há uma regra para verificação dos títulos de eleitor, o que é suficiente para a geração de números "válidos".
- Conforme a descrição do [CPF](https://pt.wikipedia.org/wiki/Cadastro_de_pessoas_f%C3%ADsicas), também é viável a geração de CPFs "válidos".
- Conforme a descrição do [CNPJ](https://pt.wikipedia.org/wiki/Cadastro_Nacional_da_Pessoa_Jur%C3%ADdica) também é factível a geração de CNPJs "válidos".
- Conforme a descrição da fórmula [LUHN](https://en.wikipedia.org/wiki/Luhn_algorithm) também é possível gerar números "válidos" de cartões de crédito.
