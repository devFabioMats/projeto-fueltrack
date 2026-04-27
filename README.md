# FuelTrack

Entrega parcial 1 da disciplina de desenvolvimento mobile.

## Escopo implementado

- Estrutura inicial de pacotes (`ui`, `model`, `data`)
- Telas iniciais em portugues
- Navegacao basica entre telas

## Estrutura principal

- `app/src/main/java/com/example/fueltrack/MainActivity.java`: tela de boas-vindas (launcher)
- `app/src/main/java/com/example/fueltrack/ui/home/HomeActivity.java`: menu inicial
- `app/src/main/java/com/example/fueltrack/ui/fuel/FuelEntryActivity.java`: formulario de abastecimento
- `app/src/main/java/com/example/fueltrack/ui/history/HistoryActivity.java`: historico simples
- `app/src/main/java/com/example/fueltrack/model/FuelRecord.java`: modelo de abastecimento
- `app/src/main/java/com/example/fueltrack/data/MockFuelDataSource.java`: dados mockados

## Fluxo de navegacao

1. Boas-vindas (`MainActivity`) -> `Comecar agora`
2. Home (`HomeActivity`) -> `Novo abastecimento` ou `Ver historico`
3. Historico (`HistoryActivity`) -> `Novo abastecimento`
4. Cadastro (`FuelEntryActivity`) -> `Salvar abastecimento` (simulacao)

## Observacao

Para executar build/testes localmente, garanta que `JAVA_HOME` esteja configurado.
