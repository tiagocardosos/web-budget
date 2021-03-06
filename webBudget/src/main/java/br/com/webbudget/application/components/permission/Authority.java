/*
 * Copyright (C) 2015 Arthur Gregorio, AG.Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.webbudget.application.components.permission;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * Mapeamento das authorities do sistema
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 24/06/2014
 */
public class Authority {

    @Getter
    @AuthorityGroup("card.authority")
    private final String CARD_VIEW = "card.authority.view";
    @Getter
    @AuthorityGroup("card.authority")
    private final String CARD_INSERT = "card.authority.insert";
    @Getter
    @AuthorityGroup("card.authority")
    private final String CARD_UPDATE = "card.authority.update";
    @Getter
    @AuthorityGroup("card.authority")
    private final String CARD_DELETE = "card.authority.delete";
    
    @Getter
    @AuthorityGroup("wallet.authority")
    private final String WALLET_VIEW = "wallet.authority.view";
    @Getter
    @AuthorityGroup("wallet.authority")
    private final String WALLET_INSERT = "wallet.authority.insert";
    @Getter
    @AuthorityGroup("wallet.authority")
    private final String WALLET_UPDATE = "wallet.authority.update";
    @Getter
    @AuthorityGroup("wallet.authority")
    private final String WALLET_DELETE = "wallet.authority.delete";
    @Getter
    @AuthorityGroup("wallet.authority")
    private final String WALLET_ADJUST_BALANCE = "wallet.authority.adjust-balance";
    
    @Getter
    @AuthorityGroup("cost-center.authority")
    private final String COST_CENTER_VIEW = "cost-center.authority.view";
    @Getter
    @AuthorityGroup("cost-center.authority")
    private final String COST_CENTER_INSERT = "cost-center.authority.insert";
    @Getter
    @AuthorityGroup("cost-center.authority")
    private final String COST_CENTER_UPDATE = "cost-center.authority.update";
    @Getter
    @AuthorityGroup("cost-center.authority")
    private final String COST_CENTER_DELETE = "cost-center.authority.delete";
    
    @Getter
    @AuthorityGroup("movement-class.authority")
    private final String MOVEMENT_CLASS_VIEW = "movement-class.authority.view";
    @Getter
    @AuthorityGroup("movement-class.authority")
    private final String MOVEMENT_CLASS_INSERT = "movement-class.authority.insert";
    @Getter
    @AuthorityGroup("movement-class.authority")
    private final String MOVEMENT_CLASS_UPDATE = "movement-class.authority.update";
    @Getter
    @AuthorityGroup("movement-class.authority")
    private final String MOVEMENT_CLASS_DELETE = "movement-class.authority.delete";
    
    @Getter
    @AuthorityGroup("movement.authority")
    private final String MOVEMENT_VIEW = "movement.authority.view";
    @Getter
    @AuthorityGroup("movement.authority")
    private final String MOVEMENT_INSERT = "movement.authority.insert";
    @Getter
    @AuthorityGroup("movement.authority")
    private final String MOVEMENT_UPDATE = "movement.authority.update";
    @Getter
    @AuthorityGroup("movement.authority")
    private final String MOVEMENT_PAY = "movement.authority.pay";
    @Getter
    @AuthorityGroup("movement.authority")
    private final String MOVEMENT_DELETE = "movement.authority.delete";

    @Getter
    @AuthorityGroup("card-invoice.authority")
    private final String CARD_INVOICE_PAY = "card-invoice.authority.pay";
    @Getter
    @AuthorityGroup("card-invoice.authority")
    private final String CARD_INVOICE_VIEW = "card-invoice.authority.view";
    @Getter
    @AuthorityGroup("card-invoice.authority")
    private final String CARD_INVOICE_PROCESS = "card-invoice.authority.process";
    
    @Getter
    @AuthorityGroup("balance-transfer.authority")
    private final String BALANCE_TRANSFER_VIEW = "balance-transfer.authority.view";
    @Getter
    @AuthorityGroup("balance-transfer.authority")
    private final String BALANCE_TRANSFER_MAKE = "balance-transfer.authority.make";
    
    @Getter
    @AuthorityGroup("financial-period.authority")
    private final String FINANCIAL_PERIOD_VIEW = "financial-period.authority.view";
    @Getter
    @AuthorityGroup("financial-period.authority")
    private final String FINANCIAL_PERIOD_INSERT = "financial-period.authority.insert";
    @Getter
    @AuthorityGroup("financial-period.authority")
    private final String FINANCIAL_PERIOD_CLOSE = "financial-period.authority.close";
    @Getter
    @AuthorityGroup("financial-period.authority")
    private final String FINANCIAL_PERIOD_DETAILS = "financial-period.authority.details";
    
    @Getter
    @AuthorityGroup("closing.authority")
    private final String CLOSING_VIEW = "closing.authority.view";
    @Getter
    @AuthorityGroup("closing.authority")
    private final String CLOSING_CLOSE = "closing.authority.close";
    @Getter
    @AuthorityGroup("closing.authority")
    private final String CLOSING_PROCESS = "closing.authority.process";
    
    @Getter
    @AuthorityGroup("account.authority")
    private final String ACCOUNTS_VIEW = "account.authority.view";
    @Getter
    @AuthorityGroup("account.authority")
    private final String ACCOUNTS_INSERT = "account.authority.insert";
    @Getter
    @AuthorityGroup("account.authority")
    private final String ACCOUNTS_UPDATE = "account.authority.update";
    @Getter
    @AuthorityGroup("account.authority")
    private final String ACCOUNTS_DELETE = "account.authority.delete";
    
    @Getter
    @AuthorityGroup("private-message.authority")
    private final String PRIVATE_MESSAGES_VIEW = "private-message.authority.view";
    @Getter
    @AuthorityGroup("private-message.authority")
    private final String PRIVATE_MESSAGES_SEND = "private-message.authority.send";
    
    /**
     * Lista todas as authorities disponiveis para uso, este metodo e utilzado
     * para criar o admin no bootstrap da aplicacao 
     * 
     * @return um set com todas as authorities disponiveis
     */
    public Set<String> getAllAvailableAuthorities() {
       
        final Set<String> authorities = new HashSet<>();

        final Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            
            field.setAccessible(true);
            
            // verifica se a permissao tem grupo de permisao
            if (field.isAnnotationPresent(AuthorityGroup.class)) {

                // adiciona as permissoes especificas
                try {
                    authorities.add((String) field.get(Authority.this));
                } catch (IllegalAccessException ex) { }
            }
        }
        return authorities;
    }
    
    /**
     * Lista todas as authorities agrupadas pelo grupo de cada uma
     * 
     * @return hashmap com os valores: grupo e itens do grupo
     */
    public HashMap<String, Set<String>> getAllAvailableAuthoritiesGrouped() {
       
        final HashMap<String, Set<String>> authorities = new HashMap<>();
        final Set<String> allAuthorities = this.getAllAvailableAuthorities();
        
        final Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            
            field.setAccessible(true);
            
            if (field.isAnnotationPresent(AuthorityGroup.class)) {
                
                final String group = field.getAnnotation(AuthorityGroup.class).value();
                
                if (!authorities.containsKey(group)) {
    
                    final Set<String> grouped = new HashSet<>();
                    
                    for (String authority : allAuthorities) {
                        if (authority.contains(group)) {
                            grouped.add(authority);
                        }
                    }
                    authorities.put(group, grouped);                
                }
            }
        }
        return authorities;
    }
}
