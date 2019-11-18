/**
 * Implementação de geração de dados para testes na área da saúde com foco em
 * dados demográficos considerados pela ABNT NBR 15985:2011.
 *
 * <p>A classe {@link com.github.kyriosdata.dados.Gerador} gera tais dados e,
 * em circunstâncias excepcionais, gera a exceção
 * {@link com.github.kyriosdata.dados.GeradorException}, que indica a
 * impossibilidade, no contexto em questão, de gerar o dado requisitado.
 * </p>
 *
 * Os tipos gerados são:
 * <ul>
 *     <li><b>inteiro</b></li> na faixa de 0 a 1000, inclusive.
 *     <li><b>inteiro (faixa)</b> desde o valor inicial até aquele final
 *     indicados, inclusive.</li>
 * </ul>
 *
 * @see com.github.kyriosdata.dados.Gerador
 * @see com.github.kyriosdata.dados.GeradorException
 */
package com.github.kyriosdata.dados;
