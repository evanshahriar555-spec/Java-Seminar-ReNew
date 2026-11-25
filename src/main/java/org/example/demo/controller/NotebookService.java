package org.example.demo.controller;
import org.example.demo.entity.*;
import org.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
@Service public class NotebookService {
 final NotebookRepository notebooks; final ProcessorRepository processors; final OpsystemRepository systems;
 public NotebookService(NotebookRepository n,ProcessorRepository p,OpsystemRepository s){notebooks=n;processors=p;systems=s;}
 public Notebook find(Integer id){return notebooks.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Notebook not found"));}
 public Notebook save(Integer id,NotebookForm f){Notebook n=id==null?new Notebook():find(id);
 n.setManufacturer(f.manufacturer());n.setType(f.type());n.setDisplay(f.display());n.setMemory(f.memory());n.setHarddisk(f.harddisk());n.setVideocontroller(f.videocontroller());n.setPrice(f.price());n.setPieces(f.pieces());
 n.setProcessor(processors.findById(f.processorid()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid processor")));
 n.setOpsystem(systems.findById(f.opsystemid()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid operating system")));return notebooks.save(n);}
 public void delete(Integer id){notebooks.delete(find(id));}
 public NotebookForm form(Notebook n){return new NotebookForm(n.getManufacturer(),n.getType(),n.getDisplay(),n.getMemory(),n.getHarddisk(),n.getVideocontroller(),n.getPrice(),n.getProcessor().getId(),n.getOpsystem().getId(),n.getPieces());}
}
