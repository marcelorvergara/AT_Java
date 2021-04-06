package br.edu.infnet;

import br.edu.infnet.produtos.Cotacao;
import br.edu.infnet.produtos.Produto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                System.out.println("4. Exibir detalhes do produto por cotação");
                System.out.println("5. Excluir uma cotação");
                System.out.println("6. Exibir cotações dentro do prazo de validade");
                System.out.println("7. Sair");

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
                        } else {
                            System.out.println("Produto não cadastrado.");
                            break;
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
                        //listar os produtos cadastrados pra selecionar um
                        System.out.println("Digite o código do produto para listar as cotações:\n");
                        for (Produto prd: produtoList){
                            System.out.println("---------------------");
                            System.out.println("Nome do Produto: " + prd.getNome()+ " Código: " + prd.getCod());
                        }

                        //lendo o cod escolhido
                        Scanner codScn = new Scanner(System.in);
                        int codProd = codScn.nextInt();

                        //pesquisando o produto
                        List<Cotacao> cotSearch = new ArrayList<>();
                        for (Cotacao cot: cotacaoList){
                            if (cot.getProduto().getCod() == codProd){
                                cotSearch.add(cot);
                            }
                        }
                        if (cotSearch.size() != 0){
                            //listar as cotações referentes ao produto
                            String nomePrd = cotSearch.get(0).getProduto().getNome();
                            System.out.println("Selecione o código da cotação para exibir os detalhes " +
                                    "das cotações referentes ao produto: " + nomePrd);
                            for (Cotacao cot : cotSearch){
                                //calculando validade cotação
                                DateTimeFormatter dt = DateTimeFormatter.ofPattern("'Proposta válida até' dd 'de' MMM 'de' yyyy\n");
                                System.out.println();
                                //listando as cotações
                                System.out.println("Fornecedor: " + cot.getFornecedor() +
                                        " | Cód. Cotação: " + cot.getCod() +
                                        " | Validade Cotação: " + cot.getDtCotacao().plusDays(cot.getValidade()).format(dt) +
                                        " | Valor R$" + cot.getValor().toString());
                            }
                            System.out.println("/n");
                        } else {
                            System.out.println("Produto não possui cotação.");
                            break;
                        }
                        break;
                    }
                    case '4': {
                        System.out.println("Entre com o código da cotação:");
                        for (Cotacao cot : cotacaoList){
                            System.out.println("Cód. Cotação: " + cot.getCod() +
                                    " Produto: " + cot.getProduto().getNome());
                        }
                        //lendo
                        Scanner scnCot = new Scanner(System.in);
                        int codCot = scnCot.nextInt();
                        //procurando na lista de cotações o produto
                        Cotacao cotSearch = cotacaoList.stream()
                                .filter( cotS -> codCot == cotS.getCod())
                                .findAny()
                                .orElse(null);
                        if (cotSearch != null){
                            Produto prd = cotSearch.getProduto();
                            //listando as infos do produto
                            System.out.println("Código do produto: " + prd.getCod()+
                                    "\nNome do produto: " + prd.getNome() +
                                    "\nDescrição do produto: " + prd.getDescricao() +
                                    "\nFabricante do produto: " + prd.getFabricante() +
                                    "\nClassificação do produto: " + prd.getClassificacao());
                        } else {
                            System.out.println("Cotação não encontrada.");
                            break;
                        }
                        break;
                    }
                    case '5': {
                        System.out.println("Entre com o código da cotação que deseja excluir:\n");
                        for (Cotacao cot : cotacaoList){
                            //calculando validade cotação
                            DateTimeFormatter dt = DateTimeFormatter.ofPattern("'Proposta válida até' dd 'de' MMM 'de' yyyy\n");
                            System.out.println("Cód. Cotação: " + cot.getCod() +
                                    " Produto: " + cot.getProduto().getNome() +
                                    " Validade: " + cot.getDtCotacao().plusDays(cot.getValidade()).format(dt) +
                                    " | Valor R$" + cot.getValor().toString() + "\n" );
                        }

                        //lendo o código da cotação que será excluída
                        Scanner scnExc = new Scanner(System.in);
                        int codExc = scnExc.nextInt();

                        //pegando a cotação que será excluida
                        Cotacao cotSearch = cotacaoList.stream()
                                .filter( cotS -> codExc == cotS.getCod())
                                .findAny()
                                .orElse(null);
                        if (cotSearch != null){
                            cotacaoList.remove(cotacaoList.indexOf(cotSearch));
                            System.out.println("Cotação excluída com sucesso.");
                        }else{
                            System.out.println("***Cotação não encontrada.***\n");
                        }
                        break;
                    }
                    case '7': {
                        System.out.println("Inté");
                        System.exit(0);
                    }
                }
            } while (!opcao.equals("7"));
        } catch (NumberFormatException e){
            System.out.println("O campo código só aceita números.");
        } catch (DateTimeParseException e){
            System.out.println("Formato da data inserida é inválida.");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Opção inválida.");
        } catch (InputMismatchException e){
            System.out.println("Entrada de dados inválida.5" +
                    "");
        }
    }
}
