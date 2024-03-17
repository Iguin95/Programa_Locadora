package aplicacao;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Cliente;
import entidades.Endereco;
import entidades.Filme;
import excecao.Excecao;
import serviço.Aluguel;
import serviço.Caixa;
import serviço.ContratoDeVenda;
import serviço.Nubank;
import serviço.Parcela;
import serviço.PicPay;
import serviço.ProcessoDeVenda;

public class Programa {

	// faça com que cada cliente tenha um id para identifica-lo mais facilmente

	private static final int CADASTRAR_CLIENTE = 1;
	private static final int CADASTRAR_FILME = 2;
	private static final int VENDA_ALUGUEL = 3;
	private static final int CONSULTAR_FILME = 4;
	private static final int CONSULTAR_CLIENTE = 5;
	private static final int SAIR = 6;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Filme filme = null;
		Cliente cliente = null;
		ContratoDeVenda cdv = null;
		ProcessoDeVenda pdv = null;

		LocalDate agora = LocalDate.now();
		Double precoTotal = null;

		List<Filme> filmes = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();

		// Menu
		System.out.println("--Sistema da Locadora de Filmes IgaraTexas--");
		System.out.println("______________________________________________");
		int op = 0;
		System.out.println();

		while (op != SAIR) {
			System.out.println("                   MENU");
			System.out.println("______________________________________________");
			System.out.println();
			System.out.println(
					" 1 - Cadastro de cliente;\n 2 - Cadastro de filme;\n 3 - Venda ou Aluguel de filme;\n 4 - Consultar filme;\n 5 - Consultar cliente;\n 6 - Sair");
			System.out.println();
			System.out.print("Digite a opção desejada: ");
			try {
				op = sc.nextInt();
				System.out.println();
				sc.nextLine();
				// Cadastro de cliente

				if (op == CADASTRAR_CLIENTE) {
					System.out.println("Cadastro de Cliente: ");
					System.out.print("Nome: ");
					String nome = sc.nextLine();

					if (nome.length() < 7) {
						int nomepeq = 0;
						while (nomepeq == 0) {
							System.out.println("Nome inválido");
							System.out.print("Nome: ");
							nome = sc.nextLine();

							if (nome.length() >= 8) {
								nomepeq = 1;
							}
						}
					}
					System.out.print("Idade: ");
					Integer idade = sc.nextInt();
					sc.nextLine();

					if (idade < 14) {
						System.out.println();
						System.out.println("Cadastro necessita de responsável!");
						System.out.println();
						continue;
					}

					System.out.print("Sexo(M/F): ");
					Character sexo = sc.next().charAt(0);
					sc.nextLine();

					System.out.print("CPF: ");
					String cpf = sc.nextLine();

					if (Cliente.validarCPF(cpf) == true) {

						System.out.print("Celular: ");
						String celular = sc.nextLine();
						System.out.println();

						System.out.println("Endereço: ");
						System.out.print("Rua ou Avenida: ");
						String ruaAvenida = sc.nextLine();
						System.out.print("Cidade: ");
						String cidade = sc.nextLine();
						System.out.print("Estado: ");
						String uf = sc.nextLine();
						System.out.print("Número da casa: ");
						Integer numeroCasa = sc.nextInt();
						sc.nextLine();
						System.out.println();

						Endereco end = new Endereco(ruaAvenida, cidade, uf, numeroCasa);

						cliente = new Cliente(nome, idade, sexo, cpf, celular, end);

						clientes.add(cliente);
					} else if (!Cliente.validarCPF(cpf)) {
						System.out.println("CPF inválido!!");
						continue;
					}

					// Cadastro de filme
				} else if (op == CADASTRAR_FILME) {
					System.out.println("Cadastro de filme:");
					System.out.print("Nome: ");
					String nome = sc.nextLine();
					System.out.print("Classificação indicativa do filme: ");
					Integer classificacao = sc.nextInt();

					System.out.print("Valor do filme: ");
					precoTotal = sc.nextDouble();

					cdv = new ContratoDeVenda(precoTotal);
					filme = new Filme(nome, classificacao, cdv);

					filmes.add(filme);

					// Venda/Aluguel
				} else if (op == VENDA_ALUGUEL) {
					System.out.println("Venda ou aluguel de filme:");
					System.out.println(" 1 - Comprar; \n 2 - Alugar; \n 3 - Voltar ");
					int op1 = sc.nextInt();
					sc.nextLine();
					while (op1 != 3) {
						if (op1 == 1) {
							System.out.println("--Comprar filme--");
							System.out.print("Insira o nome do cliente: ");
							String nome = sc.nextLine();

							for (Cliente c : clientes) {

								// boolean compNome = nome.equalsIgnoreCase(primeiroNomeCliente);/*Comparar só o
								// primeiro nome*/

								// boolean compNomeCompleto = nome.equalsIgnoreCase(c.getNome());// Compara o
								// nome todo

								boolean encontrado = false;

								System.out.print("Digite o filme que quer comprar: ");
								String nomeFilme = sc.nextLine();

								boolean compFilme = true;

								if (nome.equalsIgnoreCase(c.getNome())) {
									encontrado = true;

									int idade = c.getIdade();

									for (Filme f : filmes) {
										compFilme = nomeFilme.equalsIgnoreCase(f.getNome());
										if (compFilme) {
											System.out.println(f);

											if (f.getClassificacao() < idade) {
												System.out.print("Deseja parcelar? (S/N) - ");
												char parcelar = sc.next().charAt(0);

												cdv = new ContratoDeVenda(precoTotal, agora);

												c.adicionarFilme(f);

												if (parcelar == 'S' || parcelar == 's') {
													System.out.println("Parcelar de quantos meses? ");
													int parcelas = sc.nextInt();
													System.out.println(
															"Escolha o cartão: \n 1 - Nubank \n 2 - PicPay \n 3 - Caixa");
													int op2 = sc.nextInt();

													switch (op2) {
													case 1: {
														pdv = new ProcessoDeVenda(new Nubank());
														pdv.processarContrato(cdv, parcelas);

														for (Parcela p : cdv.getParcelas()) {
															System.out.println(p);
														}
														break;
													}
													case 2: {
														pdv = new ProcessoDeVenda(new PicPay());
														pdv.processarContrato(cdv, parcelas);

														for (Parcela p : cdv.getParcelas()) {
															System.out.println(p);
														}
														break;
													}
													case 3: {
														pdv = new ProcessoDeVenda(new Caixa());
														pdv.processarContrato(cdv, parcelas);

														for (Parcela p : cdv.getParcelas()) {
															System.out.println(p);
														}
														break;

													}
													default: {
														System.out.println("Opção inválida!");
													}

													}

												}
												if (parcelar == 'N' || parcelar == 'n') {
													System.out.println("Compra à vista!");
													op1 = 3;
												}

											} else {
												System.out.println("Idade inapropriada para o filme!");
												System.out.println();
												op1 = 3;
											}

										}

									}

								}

								if (!compFilme) {
									System.out.println("Filme inexistente!");
									System.out.println();
									op1 = 3;
								}

								if (!encontrado) {
									System.out.println("Cliente inexistente!");
									System.out.println();
									op1 = 3;
								}
							}
							// alugar filme
						} else if (op1 == 2) {
							System.out.println("--Alugar filme--");
							System.out.print("Insira o nome do cliente: ");
							String nome = sc.nextLine();
							for (Cliente c : clientes) {

								// boolean compNomeCompleto = nome.equalsIgnoreCase(c.getNome());// Compara o
								// nome todo
								boolean compFilme = true;
								boolean encontrado = false;

								if (nome.equalsIgnoreCase(c.getNome())) {
									encontrado = true;

									int idade = c.getIdade();

									System.out.print("Digite o filme que quer alugar: ");
									String nomeFilme = sc.nextLine();

									for (Filme f : filmes) {
										compFilme = nomeFilme.equalsIgnoreCase(f.getNome());

										if (compFilme == true) {
											System.out.println(f);
											if (f.getClassificacao() <= idade) {
												System.out.print("Por quantos dias deseja alugar? ");
												int alugar = sc.nextInt();

												cdv = new ContratoDeVenda(precoTotal, agora);

												c.adicionarFilme(f);

												if (alugar < 2 || alugar > 7) {
													throw new Excecao(
															"Entrada de dias incorreto. Insira um valor entre dois dias e sete dias.");
												}

												pdv = new ProcessoDeVenda();
												pdv.processarAluguel(cdv, alugar);
												double total = 0.0;
												for (Aluguel a : cdv.getAluguel()) {
													total += a.getQuantia();
													System.out.println(a);
												}

												System.out.println("Total: " + String.format("%.2f", total));

											} else {
												System.out.println("Idade inapropriada para o filme!");
												System.out.println();
												op1 = 3;
											}

										}
										if (!compFilme) {
											System.out.println("Filme inexistente!");
											System.out.println();
											op1 = 3;
										}
									}

								}
								if (!encontrado) {
									System.out.println("Cliente inexistente!");
									System.out.println();
									op1 = 3;
								}
							}
						}
					}

					// Consultar filme
				} else if (op == CONSULTAR_FILME) {
					System.out.println("Lista dos filmes:");
					for (Filme f : filmes) {
						System.out.println(f);
					}
					// Consultar cliente
				} else if (op == CONSULTAR_CLIENTE) {
					System.out.println("Lista dos clientes:");
					for (Cliente c : clientes) {
						System.out.println(c);
						System.out.println("Filme em posse: " + c.getListFilme());
						System.out.println("------------------");
					}

				}

			} catch (InputMismatchException e) {
				System.out.println("Entrada de dados incorreta --- " + e.getMessage());
				System.out.println("Pressione qualquer tecla para continuar!");
				sc.nextLine();
				op = 1;
				System.out.println();
				sc.nextLine();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Entrada de dados incorreta --- " + e.getMessage());
				System.out.println("Pressione qualquer tecla para continuar!");
				sc.nextLine();
				op = 1;
				System.out.println();
			} catch (Excecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				op = 1;
				System.out.println();
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione qualquer tecla para continuar!");
				sc.nextLine();
				op = 1;
				System.out.println();
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione qualquer tecla para continuar!");
				sc.nextLine();
				op = 1;
				System.out.println();
			}

		}

		sc.close();

	}


}
