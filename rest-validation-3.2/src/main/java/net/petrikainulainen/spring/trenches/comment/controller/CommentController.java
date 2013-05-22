package net.petrikainulainen.spring.trenches.comment.controller;

import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Petri Kainulainen
 */
@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public CommentDTO add(@Valid @RequestBody CommentDTO comment) {
        LOGGER.debug("Received comment: {}", comment);
        return comment;
    }
}
