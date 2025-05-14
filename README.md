# 💱 Conversor de Moedas em Java

Este projeto é um **Conversor de Moedas** simples e funcional, desenvolvido em **Java**, que consome dados da API de taxas de câmbio **ExchangeRate API**. O usuário pode escolher entre diferentes pares de moedas para conversão, digitar um valor e obter o resultado no console. O programa também salva o histórico de conversões em arquivos `.json` e registra logs em um arquivo `.txt`.

---

## 🧰 Tecnologias e Bibliotecas

- Java 17+
- [ExchangeRate API](https://www.exchangerate-api.com/)
- [Gson](https://github.com/google/gson) — para geração de arquivos JSON

---

## 📌 Funcionalidades

- ✅ Conversão de moedas em tempo real usando uma API externa
- ✅ Interface de texto com **ícones Unicode** e **cores ANSI**
- ✅ Salvamento do histórico de conversões em arquivos `.json`
- ✅ Geração de logs automáticos com data/hora
- ✅ Validação de entradas do usuário (números e opções de menu)

---

## 📸 Exemplo de uso no terminal
<pre>--------------------------------------------------------- 
********* 💱 Bem-vindo ao Conversor de Moedas 💱 *********
---------------------------------------------------------
============== 📄 Menu de Conversão 📄 ==============
1️⃣ Dólar (USD) >> Real (BRL)
2️⃣ Real (BRL) >> Dólar (USD)
3️⃣ Peso argentino (ARS) >> Dólar (USD)
4️⃣ Dólar (USD) >> Peso argentino (ARS)
5️⃣ Peso chileno (CLP) >> Dólar (USD)
6️⃣ Dólar (USD) >> Peso chileno (CLP)
7️⃣ Sair
====================================================
  
➡️ Escolha uma opção: 1
  
🔄 Buscando taxas de câmbio para USD...
💵 Digite o valor que deseja converter de USD para BRL: 100,00
  
============== 💱Conversão feita com sucesso💱 ==============
📈 1 USD = 5,67 BRL
💰 100,00 USD = 567,38 BRL
=============================================================
  
Pressione Enter para continuar...</pre>

---

## 🗂 Estrutura de Arquivos

<pre>ConversorDeMoedas/
├── src/pt/claiverken/conversormoeda
│ ├── Main.java
│ ├── ExchangeRateApi.java
│ ├── Moedas.java
│ ├── ConversaoFeita.java
│ └── GeradorDeFile.java
├── historico/
│ └── conversao_TIMESTAMP_UUID.json
├── log.txt
├── README.md</pre>

---

## 🖼️ Demonstração Visual

![image](https://github.com/user-attachments/assets/8a31a1f7-ae49-4305-91b0-36b98bb71392)

---

## 💾 Histórico e Logs

- Cada conversão é salva como um arquivo `.json` na pasta `historico/`, com nome baseado em **data/hora, nanoTime e UUID**.
- Cada evento (sucesso ou erro) também é registrado no arquivo `log.txt` com marcação de horário.

---

## 📦 Como executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/Claiverken/Challenge-conversor-de-moeda.git

---

## 🧑‍💻 Autor

Desenvolvido por Claiver — Estudante de Engenharia de Software e entusiasta em desenvolvimento Java.
