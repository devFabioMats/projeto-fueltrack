# FuelTrack

Entregas parciais da disciplina de desenvolvimento mobile.

## Escopo implementado

- Estrutura inicial de pacotes (`ui`, `model`, `data`)
- Telas iniciais em portugues
- Navegacao basica entre telas
- Persistencia local com Room
- Cadastro de abastecimento com validacao
- Historico com lista real de abastecimentos

## Estrutura principal

- `app/src/main/java/com/example/fueltrack/MainActivity.java`: tela de boas-vindas (launcher)
- `app/src/main/java/com/example/fueltrack/ui/home/HomeActivity.java`: menu inicial
- `app/src/main/java/com/example/fueltrack/ui/fuel/FuelEntryActivity.java`: formulario de abastecimento
- `app/src/main/java/com/example/fueltrack/ui/history/HistoryActivity.java`: historico com lista
- `app/src/main/java/com/example/fueltrack/model/FuelRecord.java`: entidade Room
- `app/src/main/java/com/example/fueltrack/data/FuelRepository.java`: acesso a dados
- `app/src/main/java/com/example/fueltrack/data/local/AppDatabase.java`: base local

## Fluxo de navegacao

1. Boas-vindas (`MainActivity`) -> `Comecar agora`
2. Home (`HomeActivity`) -> `Novo abastecimento` ou `Ver historico`
3. Historico (`HistoryActivity`) -> `Novo abastecimento`
4. Cadastro (`FuelEntryActivity`) -> `Salvar abastecimento` (simulacao)

## Observacao

Para executar build/testes localmente, garanta que `JAVA_HOME` esteja configurado.
