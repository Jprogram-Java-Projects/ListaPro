# ListaPro

## Descrição do Projeto

ListaPro é um aplicativo desktop desenvolvido em **Java** com **Swing**, que permite gerenciar tarefas (to-do list) de forma simples e intuitiva. O projeto utiliza conceitos de **POO**, **MVC**, **DAO**, e boas práticas de desenvolvimento.

## Funcionalidades

* Adicionar tarefas com descrição e prioridade (ALTA, MÉDIA, BAIXA)
* Listar todas as tarefas em uma tabela com informações de criação, conclusão, prioridade e status
* Marcar tarefas como concluídas
* Editar tarefas existentes
* Excluir tarefas
* Persistência de dados em arquivo texto (`.txt`)
* Interface gráfica amigável com cores e organização visual

## Tecnologias Utilizadas

* **Java 17+**
* **Swing** para interface gráfica
* **POO (Programação Orientada a Objetos)**
* **MVC (Model-View-Controller)** para organização do projeto
* **DAO (Data Access Object)** para manipulação de dados
* **Enum** para prioridade das tarefas
* **Utils** para manipulação de datas e arquivos (DateTimeUtils, FileUtils, UIUtils)

## Estrutura do Projeto

```
ListaPro/
 ├─ src/
 │   └─ br/com/jeff/listapro/
 │       ├─ controller/        # Classe TaskController
 │       ├─ dao/               # Interface TaskDAO e TaskFileDAO
 │       ├─ model/             # Classe Task e Enum Priority
 │       ├─ util/              # Classe DateTimeUtils, FileUtils e UIUtils
 │       ├─ view/              # Classes TaskView, TaskFormPanel, TaskTablePanel, TaskActionsPanel, EditTaskDialog
 │       └─ Main.java          # Classe com método main
 └─ resources/
     └─ icons/                # Ícones do projeto
```

## Design e Padrões de Projeto

* **MVC (Model-View-Controller)**: separa a lógica de negócio (Controller), a interface (View) e os dados (Model)
* **DAO**: TaskDAO define operações de persistência; TaskFileDAO implementa com arquivo texto
* **SOLID**: aplicado para manter código limpo, modular e de fácil manutenção
* **Enums**: Priority define as prioridades de forma segura
* **Utils**: DateTimeUtils para manipulação e formatação de datas, FileUtils para leitura e escrita de arquivos, UIUtils para estilização de botões e componentes

## Funcionalidades Visuais

* Interface elegante com cores suaves e botões destacados
* Tabela com cores diferentes para status das tarefas (`Concluído` verde, `Em andamento` vermelho)
* Botões com texto e ícones, alinhados e com tamanhos ajustáveis
* Janela de edição de tarefas (`EditTaskDialog`) com campos pré-preenchidos e fácil interação

## Como Rodar

### Pelo IDE

1. Clonar o repositório
2. Abrir o projeto em sua IDE Java (IntelliJ, Eclipse, etc.)
3. Garantir que a pasta `resources/icons` esteja configurada como **Resources Root**
4. Executar a classe `Main.java` para iniciar a aplicação

### Pelo Terminal / Prompt de Comando

1. Navegar até a pasta raiz do projeto onde está o `src`
2. Compilar todas as classes:

```bash
javac -d bin src/br/com/jeff/listapro/**/*.java
```

3. Executar a aplicação:

```bash
java -cp bin br.com.jeff.listapro.Main
```

> Certifique-se de que a pasta `resources/icons` esteja acessível e no mesmo diretório do `bin` ou configurada no classpath.

## Estrutura de Classes

* **Model**: Task.java, Priority.java
* **DAO**: TaskDAO.java (interface), TaskFileDAO.java (implementação)
* **Controller**: TaskController.java
* **View**:

  * TaskView\.java
  * TaskFormPanel.java
  * TaskTablePanel.java
  * TaskActionsPanel.java
  * EditTaskDialog.java
* **Utils**: DateTimeUtils.java, FileUtils.java, UIUtils.java

## Observações

* Persistência em arquivo texto simples, ID gerado incrementalmente pelo DAO
* Data de criação automática ao adicionar tarefa
* Data de conclusão preenchida ao marcar tarefa como concluída
* Botão de edição desbloqueia apenas quando uma linha da tabela é selecionada

## Contribuições

Este projeto serve como referência para aprendizado em Java Desktop, uso de Swing e boas práticas de programação. Sugestões de melhorias e novos recursos são bem-vindas.

---

> **ListaPro** - Aplicativo de gerenciamento de tarefas em Java com Swing.
