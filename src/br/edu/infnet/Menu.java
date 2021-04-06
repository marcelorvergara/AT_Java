package br.edu.infnet;

import br.edu.infnet.produtos.Cotacao;
import br.edu.infnet.produtos.Produto;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Menu {

    //lista de produtos cadastrados
    private static List<Produto> produtoList = new ArrayList<>();
    //lista de cotações
    private static List<Cotacao> cotacaoList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        //mock data produtos
        Produto prd1 = new Produto(122, "Coca-Cola", "Coca-Cola Company", "Refrigerante para as festinhas", "Alimentação");
        Produto prd2 = new Produto(892,"Coxinha","Dona Moçoroca","Salgadinho para festas", "Alimentação");
        Produto prd3 = new Produto(837,"Gasolina","Petrobrás", "Combustível para foguetes", "Locomoção");
        Produto prd4 = new Produto(233,"Açucar","União", "Veneno para adoçar a vida", "Alimentação");
        Produto prd5 = new Produto(222, "Televisão", "Sony", "Produto para alienação das pessoas", "Imobiliário");
        //mock data lista de produtos
        produtoList = Arrays.asList(prd1, prd2, prd3, prd4, prd5);
        //mock data cotações
        cotacaoList = Arrays.asList(new Cotacao(239,prd1,"Extra",LocalDate.of(2021, 4,10),30, 1, new BigDecimal("129.99")),
                new Cotacao(123,prd1,"Mundial", LocalDate.of(2021, 4, 4),40,1,new BigDecimal("140.09")),
                new Cotacao(901,prd1,"Prezunic", LocalDate.of(2021, 4, 1), 14,1, new BigDecimal("123.95")),
                new Cotacao(196,prd3,"Shell", LocalDate.of(2021, 4, 4), 20,1,new BigDecimal("299.00")));

        try{
            //lendo a escolha do usuário na tela
            Scanner scn = new Scanner(System.in);
            String opcao = null;

            //loop para menu
            do{
                //mostrando o menu
                System.out.println("\n\n");
                System.out.println("**----------:: Escolha a opção abaixo ::-----------**");
                System.out.println("1. --- Cadastrar Produto ----------------------------");
                System.out.println("2. --- Cadastrar Cotação ----------------------------");
                System.out.println("3. --- Consultar Corações por Produto ---------------");
                System.out.println("4. --- Exibir detalhes do produto por cotação -------");
                System.out.println("5. --- Excluir uma cotação --------------------------");
                System.out.println("6. --- Exibir cotações dentro do prazo de validade --");
                System.out.println("7. --- Listar produtos cadastrados ------------------");
                System.out.println("8. --- Listar cotações cadastradas ------------------");
                System.out.println("9. --- Alterar Produto ou Cotação -------------------");
                System.out.println("Q. -------------------------Sair---------------------");

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
                        System.out.println("Entre com o tipo/classificação do produto:");
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
                            System.out.println("Nome do Produto: " + prd.getNome() +
                                    " / Código: " + prd.getCod());
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
                        ctao.setValor(new BigDecimal(valor.replace(",",".")));

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
                            System.out.println("Nome do Produto: " + prd.getNome()+
                                    " / Código: " + prd.getCod());
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
                            System.out.println("\nCotações referentes ao produto: " + nomePrd);
                            for (Cotacao cot : cotSearch){
                                //calculando validade cotação
                                DateTimeFormatter dt = DateTimeFormatter.ofPattern("'Proposta válida até' dd 'de' MMM 'de' yyyy\n");
                                System.out.println();
                                //listando as cotações
                                System.out.println("Fornecedor: " + cot.getFornecedor() +
                                        " | Cód. Cotação: " + cot.getCod() +
                                        " | Validade Cotação: " + cot.getDtCotacao().plusDays(cot.getValidade()).format(dt) +
                                        " | Valor R$" + cot.getValor().toString().replace(".",","));
                            }
                        } else {
                            System.out.println("\n*** Produto não possui cotação. ***\n");
                            break;
                        }
                        break;
                    }
                    case '4': {
                        System.out.println("Entre com o código da cotação:");
                        for (Cotacao cot : cotacaoList){
                            System.out.println("Cód. Cotação: " + cot.getCod() +
                                    " / Produto: " + cot.getProduto().getNome());
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
                                    " | Valor R$" + cot.getValor().toString().replace(".",",") + "\n" );
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
                    case '6': {
                        DateTimeFormatter dt = DateTimeFormatter.ofPattern("'Proposta válida até' dd 'de' MMM 'de' yyyy\n");
                        for (Cotacao cot : cotacaoList){
                            LocalDate ldLimite = cot.getDtCotacao().plusDays(cot.getValidade());
                            LocalDate hoje = LocalDate.now();
                            if (!hoje.isAfter(ldLimite) ){
                                System.out.println("Cód. Cotação: " + cot.getCod() +
                                        "\n | Produto: " + cot.getProduto().getNome() +
                                        "\n | Fornecedor: " + cot.getFornecedor() +
                                        "\n | Validade da cotação: " + dt.format(cot.getDtCotacao())+
                                        "\n | Valor: R$" + cot.getValor() +
                                        "\n");
                            }
                        }
                        break;
                    }
                    case '7': {
                        System.out.println("\nLista de Produtos Cadastrados:");
                        for (Produto prd: produtoList) {
                            System.out.println("Cód. Produto: " + prd.getCod() +
                                    " / Nome: " + prd.getNome() +
                                    " / Tipo: " + prd.getClassificacao() +
                                    " / Descrição: " + prd.getDescricao());
                        }
                        break;
                    }
                    case '8': {
                        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E, dd/MM/yyyy");
                        LocalDate hoje = LocalDate.now();
                        System.out.println("\nLista de Cotações Cadastradas:");
                        for (Cotacao cot: cotacaoList){
                            String condi = null;
                            if (hoje.isAfter(cot.getDtCotacao().plusDays(cot.getValidade()))){
                                condi = "Não";
                            } else
                            {
                                condi = "Sim";
                            }
                            System.out.println("Cód. Cotação: " + cot.getCod() + "\n" +
                                    " | Produto: " + cot.getProduto().getNome() + "\n" +
                                    " | Fornecedor: " + cot.getFornecedor() + "\n" +
                                    " | Dia da Cotação: " + dtf1.format(cot.getDtCotacao()) + "\n" +
                                    " | Validade: " + cot.getValidade() + "\n" +
                                    " | Quantidade: " + cot.getQuantidade() + "\n" +
                                    " | Valor: " + cot.getValor() + "\n" +
                                    " | Dentro do Prazo? " + condi  +
                                    " \n-----------------------------");
                        }
                        break;
                    }
                    case '9': {
                        System.out.println("Digite 1 para alterar produto ou 2 para alterar cotaçao:");
                        Scanner op = new Scanner(System.in);
                        int opR = op.nextInt();
                        if (opR == 1){
                            System.out.println("Indira o código do produto que deseja alterar: ");
                            //listando os produtos cadastrados
                            for (Produto prd: produtoList){
                                System.out.println("Nome: " + prd.getNome() + " / Cód. Produto: " + prd.getCod());
                            }
                            //lendo a escolha do usuário
                            Scanner scnPrd = new Scanner(System.in);
                            int codPrd = scnPrd.nextInt();

                            //procurando o index do produto
                            int index = -1;
                            for (Produto prd: produtoList){
                                if (prd.getCod() == codPrd){
                                    index = produtoList.indexOf(prd);
                                }
                            }

                            //criando um novo produto para substituir o antigo
                            Produto novoProduto = produtoList.get(index);
                            //lendo os novos dados
                            Scanner nvScn = new Scanner(System.in);

                            System.out.println("Entre com um novo código ou utilize o código " + produtoList.get(index).getCod());
                            String nvCod = nvScn.nextLine();
                            if (!nvCod.equals("")){
                                novoProduto.setCod(Integer.parseInt(nvCod));
                            }
                            System.out.println("Entre com um novo nome ou utilize o nome " + produtoList.get(index).getNome());
                            String nvNome = nvScn.nextLine();
                            if (!nvNome.equals("")){
                                novoProduto.setNome(nvNome);
                            }
                            System.out.println("Entre com um novo tipo/classificação ou utilize " + produtoList.get(index).getClassificacao());
                            String nvClass = nvScn.nextLine();
                            if (!nvClass.equals("")){
                                novoProduto.setClassificacao(nvClass);
                            }
                            System.out.println("Entre com uma nova descrição ou utilize " + produtoList.get(index).getDescricao());
                            String nvDesc = nvScn.nextLine();
                            if (!nvDesc.equals("")){
                                novoProduto.setDescricao(nvDesc);
                            }
                            System.out.println("Entre com um novo fabricante ou utilize " + produtoList.get(index).getFabricante());
                            String nvFabr = nvScn.nextLine();
                            if (!nvFabr.equals("")){
                                novoProduto.setFabricante(nvFabr);
                            }
                            produtoList.set(index,novoProduto);
                            break;
                        }else if(opR == 2){
                            System.out.println("Digite o código da cotação que deseja alterar:");
                            for (Cotacao cot: cotacaoList){
                                System.out.println("Cód. Cotação " + cot.getCod() + " | Produto " + cot.getProduto().getNome());
                            }
                            Scanner scnCot = new Scanner(System.in);
                            int cotCod = scnCot.nextInt();

                            int index = -1;
                            for (Cotacao cotNew : cotacaoList){
                                if (cotNew.getCod() == cotCod){
                                    index = cotacaoList.indexOf(cotNew);
                                }
                            }
                            //criando uma nova cotação para substituir a escolhida pelo user
                            Cotacao nvCot = cotacaoList.get(index);
                            //lendo as infos
                            Scanner scnNv = new Scanner(System.in);
                            System.out.println("Entre com um novo código ou use " + nvCot.getCod());
                            String nvCod = scnNv.nextLine();
                            if (!nvCod.equals("")){
                                nvCot.setCod(Integer.parseInt(nvCod));
                            }
                            System.out.println("Entre com uma nova empresa ou use " + nvCot.getFornecedor());
                            String nvForn = scnNv.nextLine();
                            if (!nvForn.equals("")){
                                nvCot.setFornecedor(nvForn);
                            }
                            System.out.println("Entre com uma nova validade ou use " + nvCot.getValidade());
                            String nvVal = scnNv.nextLine();
                            if (!nvVal.equals("")){
                                nvCot.setValidade(Integer.parseInt(nvVal));
                            }

                            System.out.println("Entre com uma nova quantidade ou use " + nvCot.getQuantidade());
                            String nvQtd = scnNv.nextLine();
                            if (!nvQtd.equals("")){
                                nvCot.setQuantidade(Integer.parseInt(nvQtd));
                            }
                            System.out.println("Entre com um novo valor ou use " + nvCot.getValor());
                            String nvValor = scnNv.nextLine();
                            if (!nvValor.equals("")){
                                nvCot.setValor(new BigDecimal(nvValor.toString().replace(",",".")));
                            }
                            cotacaoList.set(index,nvCot);
                            break;
                        }else {
                            System.out.println("Opçao invalida");
                            break;
                        }

                    }
                    case 'Q': {
                        System.out.println("Inté");
                        System.exit(0);
                    }
                }
            } while (!opcao.equals("Q"));
        } catch (NumberFormatException e){
            System.out.println("O campo código só aceita números.");
        } catch (DateTimeParseException e){
            System.out.println("Formato da data inserida é inválida.");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Opção inválida.");
        } catch (InputMismatchException e){
            System.out.println("Entrada de dados inválida.");
        } catch (DateTimeException e) {
            System.out.println("A data deve obedecer a formatação DD MM AAAA");
        }
    }
}
