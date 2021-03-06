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

package br.com.webbudget.application.controller;

import br.com.webbudget.application.exceptions.ApplicationException;
import br.com.webbudget.domain.entity.users.User;
import br.com.webbudget.domain.service.AccountService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBean que contem os metodos de autenticacao do usuario, nele e feita a invo-<br/>
 * cacao do metodo de autenticacao e tambem a validacao dos dados informados
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 06/10/2013
 */
@ViewScoped
@ManagedBean
public class AuthenticationBean extends AbstractBean {
    
    @Getter
    private User user;
    
    @Setter
    @ManagedProperty("#{accountService}")
    private transient AccountService accountService;

    /**
     * 
     * @return 
     */
    @Override
    protected Logger initializeLogger() {
        return LoggerFactory.getLogger(AuthenticationBean.class);
    }
    
    /**
     * 
     */
    @PostConstruct
    public void initialize() {
        this.user = new User();
    }
    
    /**
     * Realiza o login, se houver erro redireciona para a home novamente e <br/>
     * impede que prossiga
     * 
     * @return a home autenticada ou a home de login caso acesso negado
     */
    public String doLogin() {

        try {
            this.accountService.login(this.user);
            return "/main/dashboard.xhtml?faces-redirect=true";
        } catch (ApplicationException ex) {
            this.error(ex.getMessage(), true);
            return null;
        }
    }
}
