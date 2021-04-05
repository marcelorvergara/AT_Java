package br.edu.infnet;

import br.edu.infnet.produtos.Cotacao;
import br.edu.infnet.produtos.Produto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    //lista de produtos cadastrados
    private static List<Produto> produtoList = new ArrayList<>();
    //lista de cotações
    private static List<Cotacao> cotacaoList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        try{
            //lendo a escolha do usuário na tela
            Scanner scn = new Scanner(System.in);
            String opcao = null;

            //loop para menu
            do{
                //mostrando o menu
                System.out.println("---Escolha a opção abaixo:");
                System.out.println("1. Cadastrar Produto");
                System.out.println("2. Cadastrar Cotação");
                System.out.println("3. Consultar Corações por Produto");
                System.out.println("4. Exibir produtos por cotação");
                System.out.println("5. Sair");

                opcao = scn.nextLine();

                //encaminhando o usuário de acordo com a escolha na tela
                switch (opcao.charAt(0)){
                    case '1':{
                        //objeto produto a ser inserido na lista de produtos
                        Produto prd = new Produto();

                        //lendo as informações sobre o produto a ser inserido
                        Scanner prdScn = new Scanner(System.in);

                        //nome
                        System.out.println("Entre com o nome do produto:");
                        String nomePrd = prdScn.nextLine();
                        prd.setNome(nomePrd);
                        //fabricante
                        System.out.println("Entre com o fabricante:");
                        String fabPrd = prdScn.nextLine();
                        prd.setFabricante(fabPrd);
                        //descrição do prd
                        System.out.println("Entre com a descrição:");
                        String descPrd = prdScn.nextLine();
                        prd.setDescricao(descPrd);
                        //classificação prd
                        System.out.println("Entre com o tipo de produto:");
                        String classPrd = prdScn.nextLine();
                        prd.setClassificacao(classPrd);
                        //codigo prd
                        System.out.println("Entre com o código do produto:");
                        int codPrd = Integer.parseInt(prdScn.nextLine());
                        prd.setCod(codPrd);

                        //inserindo o produto na lista de produtos
                        produtoList.add(prd);
                        //fechando o scanner
                        //scn.close();
                        break;
                    }

                    case '2':{
                        //objeto cotação que será inserido na lista de cotações
                        Cotacao ctao = new Cotacao();

                        //lendo as informações sobre a cotação que será inserida
                        Scanner ctaoScn = new Scanner(System.in);

                        //listar os produtos cadastrados pra selecionar um
                        System.out.println("Digite o código do produto a ser cadastrado na cotação:\n");
                        for (Produto prd: produtoList){
                            System.out.println("---------------------");
                            System.out.println("Nome do Produto: " + prd.getNome()+ " Código: " + prd.getCod());
                        }
                        int codProduto = Integer.parseInt(ctaoScn.nextLine());
                        //buscando o produto escolhido
                        Produto prdSearch = produtoList.stream()
                                .filter( produto -> codProduto == produto.getCod())
                                .findAny()
                                .orElse(null);
                        if (prdSearch != null){
                            ctao.setProduto(prdSearch);
                        }
                        //inserir cod cotação
                        System.out.println("Insira o código da cotação:");
                        int codCtao = Integer.parseInt(ctaoScn.nextLine());
                        ctao.setCod(codCtao);
                        //fornecedor
                        System.out.println("Insira o fornecedor:");
                        String fornCtao = ctaoScn.nextLine();
                        ctao.setFornecedor(fornCtao);
                        //data cotação
                        System.out.println("Entre com o ano da cotação (AAAA):");
                        int anoCtao = Integer.parseInt(ctaoScn.nextLine());
                        System.out.println("Entre com o mês da cotação (MM):");
                        int mesCtao = Integer.parseInt(ctaoScn.nextLine());
                        System.out.println("Entre com o dia da cotação(DD)");
                        int diaCtao = Integer.parseInt(ctaoScn.nextLine());
                        LocalDate data = LocalDate.of(anoCtao,mesCtao,diaCtao);
                        ctao.setDtCotacao(data);
                        //validade cotação
                        System.out.println("Entre com a validade da cotação em dias:");
                        int validade = Integer.parseInt(ctaoScn.nextLine());
                        ctao.setValidade(validade);
                        //quantidade
                        System.out.println("Qual a quantidade de itens contidos na cotação?");
                        int qtd = Integer.parseInt(ctaoScn.nextLine());
                        ctao.setQuantidade(qtd);
                        //valor
                        System.out.println("Qual o valor da cotação?");
                        String valor = ctaoScn.nextLine();
                        ctao.setValor(new BigDecimal(valor));

                        //inserindo a cotação na lista de cotações
                        cotacaoList.add(ctao);
                        //fechando scanner
                        //ctaoScn.close();
                        DateTimeFormatter dt = DateTimeFormatter.ofPattern("'Proposta válida até' dd 'de' MMM 'de' yyyy\n");
                        System.out.println(data.plusDays(validade).format(dt));
                        break;
                    }
                    case '3': {

                        break;
                    }
                    case '5': {
                        System.out.println("Inté");
                        System.exit(0);
                    }
                }
            } while (!opcao.equals("5"));
        } catch (NumberFormatException e){
            System.out.println("O campo código só aceita números.");
        } catch (DateTimeParseException e){
            System.out.println("Formato da data inserida é inválida.");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Opção inválida.");
        }
    }
}
