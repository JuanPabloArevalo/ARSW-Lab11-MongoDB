/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.restcontrollers;

import edu.eci.arsw.collabhangman.model.game.entities.User;
import edu.eci.arsw.collabhangman.persistence.PersistenceException;
import edu.eci.arsw.collabhangman.services.GameServices;
import edu.eci.arsw.collabhangman.services.GameServicesException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/users")
public class UsersResourceController {

    @Autowired
    GameServices gameServices;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {
        try {
//            User usuario = gameServices.loadUserData(userId);
            return new ResponseEntity<>(gameServices.getPuntajeMaximo(userId), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "/score/{score}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserWithScoreMoreThan(@PathVariable Integer score) {
        try {
            return new ResponseEntity<>(gameServices.getUsersWithScoreMoreThan(score), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UsersResourceController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
