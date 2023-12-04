package br.com.feltex.bot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class EchoBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return DadosBot.BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return DadosBot.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            var mensagem = responder(update);
            try {
                execute(mensagem);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage responder(Update update) {
        String textoMensagem = update.getMessage().getText().toLowerCase();
        String chatId = update.getMessage().getChatId().toString();

        String resposta = "";

        if (textoMensagem.startsWith("/start")) {
            resposta = "\uD83E\uDD16 Por favor, informe o código do cliente (6 dígitos):";
        } else if (textoMensagem.startsWith("12345") || textoMensagem.startsWith("12345")) {
            resposta = "\uD83E\uDD16 Olá Joaquim Ramos.\nEm que posso ajudar?\n\nProduto\nPedido\nCadastro";
        } else if (textoMensagem.startsWith("Produto") || textoMensagem.startsWith("produto")) {
            resposta = "\uD83E\uDD16 Escolha 1 opção:\n1-Ingredientes\n2-Preço Unitário\n3-Lote Mínimo\n4-Validade\n5-Sugestões\n8-Falar com Atendente\n9-Voltar";
        } else if (textoMensagem.startsWith("1")) {
            resposta = "Os ingredientes dos refrigerantes e limonadas da Empresa da Lima & Cola são:🥤 Água 🥤 Açúcar 🥤 Ácido cítrico 🥤 Aromatizantes naturais e/ou artificiais 🥤 Corantes naturais e/ou artificiais 🥤 Conservantes 🥤 Gás carbônico. \n\nEsses são os ingredientes básicos, mas cada sabor pode ter ingredientes adicionais específicos.\n\n9-Voltar";
        } else if ("2".equals(textoMensagem)) {
            resposta = "\u26A0\uFE0FPreço indisponível.\u26A0\uFE0F\n\nPara mais informações de pedido, entre em contato com +5511937065365\n\nPara Atendimento ao Cliente, entre em contato com +5511933906682\n\n8-Falar com Atendente.\n9-Voltar";
        } else if ("3".equals(textoMensagem)) {
            resposta = "Quantidade mínima de compra: 5 engradados (sabores podem variar)\n\n8-Falar com Atendente.\n9-Voltar";
        } else if ("4".equals(textoMensagem)) {
            resposta = "Validade dos Produtos: 180 dias a partir da data de fabricação.\n\n8-Falar com Atendente.\n9-Voltar";
        } else if ("5".equals(textoMensagem)) {
            resposta = "\u260E+5511937065365\nCall Center de Pedidos. \n\n\u260E+5511933906682\nSAC // Atendimento ao cliente\n\n9-Voltar";
        } else if ("9".equals(textoMensagem)) {
            resposta = "\uD83E\uDD16 Olá Joaquim Ramos.\nEm que posso ajudar?\n\nProduto\nPedido\nCadastro";
        } else if ("8".equals(textoMensagem)) {
            resposta = "\u260E+5511937065365\nCall Center de Pedidos.\n\n\u260E+5511933906682\nSAC // Atendimento ao Cliente\n\n9-Voltar";
        } else if (textoMensagem.contains("Pedido") || textoMensagem.contains("pedido")) {
            resposta = "\uD83E\uDD16Escolha 1 opção:\nStatus\nPrazo da Entrega\nNota Fiscal\nReembolso\n\n8-Falar com Atendente\n9-Voltar";
        }else if (textoMensagem.startsWith("Status")) {
            resposta = "teste";
        } else if (textoMensagem.startsWith("Prazo da Entrega")) {
            resposta = "Informação indisponível\nEntre em contato com um atendente";
        }

        if (textoMensagem.contains("Cadastro") || textoMensagem.contains("cadastro")) {
            resposta = "\uD83E\uDD16Escolha 1 opção:\nAlterar telefone\nAlterar endereço\nAlterar e-mail\n\n8-Falar com Atendente\n9-Voltar";
        } else if (textoMensagem.startsWith("Alterar telefone")) {
            resposta = "Informação indisponível";
        } else if (textoMensagem.startsWith("Prazo da Entrega")) {
            resposta = "Informação indisponível";
        }

        // Verifica se resposta é vazia e fornece uma mensagem padrão se necessário
        if (resposta.isEmpty()) {
            resposta = "\u26A0\uFE0FInformação indisponível\u26A0\uFE0F\n\nPara mais informações de pedido, entre em contato com +5511937065365\nPara Atendimento ao Cliente, entre em contato com +5511933906682\n\n8-Falar com Atendente.\n9-Voltar";
        }

        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

}