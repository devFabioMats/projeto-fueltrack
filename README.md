# FuelTrack

Projeto prático desenvolvido para a disciplina de Desenvolvimento Mobile. O FuelTrack é um aplicativo Android nativo para controle de abastecimentos de veículos, permitindo ao usuário gerenciar custos e quilometragem de forma intuitiva.

## Escopo Implementado (Desenvolvimento Avançado)

- **Estrutura de Pacotes**: Organização robusta em camadas (`ui`, `model`, `data`, `local`).
- **Persistência Local**: Uso da biblioteca **Room** para armazenamento persistente em banco de dados SQLite.
- **CRUD Completo**:
    - **Create**: Cadastro de novos abastecimentos com validações.
    - **Read**: Histórico completo de registros ordenados por data.
    - **Update**: Edição de registros existentes ao tocar em um item do histórico.
    - **Delete**: Exclusão de registros via clique longo com confirmação.
- **Interface e UX**:
    - Design modernizado com **Material Design 3**.
    - Paleta de cores temática ("Fuel") e identidade visual com **Logo personalizada**.
    - Uso de **Floating Action Button (FAB)** no histórico.
    - Entrada de datas facilitada via **DatePickerDialog** nativo.
    - Feedback em tempo real com `TextInputLayout` e `Toast`.

## Estrutura Principal

- `app/src/main/java/com/example/fueltrack/MainActivity.java`: Tela de boas-vindas com logo (Launcher).
- `app/src/main/java/com/example/fueltrack/ui/home/HomeActivity.java`: Menu principal com acesso rápido.
- `app/src/main/java/com/example/fueltrack/ui/fuel/FuelEntryActivity.java`: Formulário inteligente para cadastro e edição.
- `app/src/main/java/com/example/fueltrack/ui/history/HistoryActivity.java`: Lista de histórico com suporte a ações de CRUD.
- `app/src/main/java/com/example/fueltrack/model/FuelRecord.java`: Entidade de dados do Room.
- `app/src/main/java/com/example/fueltrack/data/FuelRepository.java`: Camada de abstração de dados (Repository Pattern).
- `app/src/main/res/drawable/ic_logo_fuel.xml`: Recurso vetorial da logo do projeto.

## Fluxo de Navegação

1. **Boas-vindas** (`MainActivity`) -> `Começar agora`
2. **Home** (`HomeActivity`) -> `Novo Abastecimento` ou `Ver Histórico`
3. **Histórico** (`HistoryActivity`) -> 
    - Clique no item: **Editar**
    - Clique longo no item: **Excluir**
    - FAB: **Novo Registro**
4. **Cadastro/Edição** (`FuelEntryActivity`) -> Salvar alterações e retornar.

## Demonstração em Vídeo

Assista à demonstração das funcionalidades do FuelTrack no link abaixo:
- [Vídeo de Demonstração - FuelTrack](URL_DO_VIDEO_AQUI)

## Observação

Para executar build/testes localmente, garanta que `JAVA_HOME` esteja configurado corretamente para o Java 17 ou superior.
