Decisões de projeto

1- Uso do Compose ao inves do XML:
	
 - Layouts mais dinamicos e flexiveis;
 - Redução de boilerplate no projeto;
 - Desenvolvimento mais agil com suporte a pré-visualização;
 - Possivel interação com XML se necessário;

2- Injeção de dependencia (Koin)
	
 - Simples e declarativo, não exige configurações complexas;
	
 - Desenvolvimento especifico para o Kotlin, aproveitando suas características;
	
 - Facilidade para testes a serem implementados no futuro;

3- Ktor
	
 - Como é feita totalmente em kotlin, sua interação com o kotlin fica mais natural, além de aproveitar de forma total as extensões e funcionalidades do kotlin.

 - Configuração personalizavel de requisições de rede;
	
 - Integrado ao uso de Coroutines do kotlin;

4- Room
	
 - Abstração do SQLite;
	
 - Persistencia dos dados salvos pelo usuário no dispositivo do mesmo;
	
 - Abordagem recomendada pela Google;
	
 - Annotations para definição do banco de dados;
	
 - Suporte a consultas complexas;
	
 - Integração com LiveData e Flow;

5- WorkManager
	
 - Execução de tarefas agendadas com a aplicação aberta, em backgroud ou fechada;
	
 - Integração com Coroutine;
	
 - Integração com Koin;

6 - MVVM
	
 - Desacoplamento, oque facilitará testes futuros e reutilização de código, se necessário;
	
 - Ciclo de Vida (evita memory leaks) e preserva dados;
	
 - Recomendada pelo Google, através do Android Jetpack;

Adendos: 
	
 - Salvamento de apenas um objeto no banco de dados:

   - Como a orientação do exercicios esta no singular, presume-se que deve ser salvo apenas o ultimo item
			em que houve o clique no botão "Eu Quero", fazendo-se necessária, a exclusão do item antecessor e a adição do novo item.

Etapas futuras:
	
 - Testes unitarios a fim de trazer mais segurança;
	
 - Verificações de conexões de rede e versões de dispositivos onde necessario;
	
 - Adequação do tempo correto ao WorkManager (foi adiciona um tempo default - 15 minutos)
	
