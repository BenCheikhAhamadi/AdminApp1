package sn.ucad.ben.adminapp1.services;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.ben.adminapp1.dto.UtilisateurDto;
import sn.ucad.ben.adminapp1.exception.EntityNotFoundException;
import sn.ucad.ben.adminapp1.exception.RequestException;
import sn.ucad.ben.adminapp1.mapper.UtilisateurMapper;
import sn.ucad.ben.adminapp1.repositories.IUtilisateurRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UtilisateurService {
    private IUtilisateurRepository iUtilisateurRepository;
    private UtilisateurMapper utilisateurMapper;
    MessageSource messageSource;

    public UtilisateurService(IUtilisateurRepository iUtilisateurRepository, UtilisateurMapper utilisateurMapper, MessageSource messageSource) {
        this.iUtilisateurRepository = iUtilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.messageSource = messageSource;
    }
    @Transactional(readOnly = true)
    public List<UtilisateurDto> getUtilisateur(){
        return StreamSupport.stream(iUtilisateurRepository.findAll().spliterator(),false)
                .map(utilisateurMapper::toUtilisateur).collect(Collectors.toList());
    }
    @Transactional
    public UtilisateurDto getUtilisateurById(int id){
        return utilisateurMapper.toUtilisateur(iUtilisateurRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("user.notfound",new Object[]{id},
                        Locale.getDefault()))));

    }

     public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto){
        iUtilisateurRepository.findById(utilisateurDto.getId())
                .ifPresent(utilisateur->{
            throw new RequestException(
                    messageSource.getMessage("user.exists",
                    new  Object[]{utilisateurDto.getId()},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        });
            return utilisateurMapper.toUtilisateur(iUtilisateurRepository.save(utilisateurMapper.fromUtilisateur(utilisateurDto)));
     }
     public UtilisateurDto updateUtilisateur( int id,UtilisateurDto utilisateurDto) {

         return iUtilisateurRepository.findById(id)
                 .map(entity -> {
                     utilisateurDto.setId(id);
                     return utilisateurMapper.toUtilisateur(iUtilisateurRepository.save(utilisateurMapper.fromUtilisateur(utilisateurDto)));
                 }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                         Locale.getDefault())));

     }
     public void deleteUser(int id){
        try {
            iUtilisateurRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("user.errordeletion",new Object[]{id},
                    Locale.getDefault())
                    ,HttpStatus.CONFLICT);
        }
     }

}
