package sn.ucad.ben.adminapp1.services;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.ben.adminapp1.dto.ProduitDto;
import sn.ucad.ben.adminapp1.exception.EntityNotFoundException;
import sn.ucad.ben.adminapp1.exception.RequestException;
import sn.ucad.ben.adminapp1.mapper.ProduitMapper;
import sn.ucad.ben.adminapp1.repositories.IProduitRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProduitService {

    private IProduitRepository iProduitRepository;
    private ProduitMapper produitMapper;
    MessageSource messageSource;

    public ProduitService(IProduitRepository iProduitRepository, ProduitMapper produitMapper, MessageSource messageSource) {
        this.iProduitRepository = iProduitRepository;
        this.produitMapper = produitMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<ProduitDto> getProduitDto() {
        return StreamSupport.stream(iProduitRepository.findAll().spliterator(), false)
                .map(produitMapper::toProduit).collect(Collectors.toList());
    }

    @Transactional
    public ProduitDto getProuitById(int id) {
        return produitMapper.toProduit(iProduitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    public ProduitDto createProduit(ProduitDto produitDto) {
        iProduitRepository.findById(produitDto.getId()).ifPresent(produit -> {
            throw new RequestException(messageSource.getMessage("produit.exists",
                    new Object[]{produitDto.getId()},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        });
        return produitMapper.toProduit(iProduitRepository.save(produitMapper.fromProduit(produitDto)));
    }

    public ProduitDto updateProduit(int id, ProduitDto produitDto) {
        return iProduitRepository.findById(id)
                .map(entity -> {
                    produitDto.setId(id);
                    return produitMapper.toProduit(iProduitRepository.save(produitMapper.fromProduit(produitDto)));

                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                        Locale.getDefault())));

    }

    public void produitDelete(int id){
        try {
            iProduitRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("produit.errordeletion",new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }

    }
}
