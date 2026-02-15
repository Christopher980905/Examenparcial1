import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Pagos } from '../../model/pagos';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { PagosService } from '../../services/pagos';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-pagos',
  standalone: false,
  templateUrl: './pagos.html',
  styleUrl: './pagos.css',
})
export class PagosComponent implements OnInit {

  @ViewChild('formularioPagos') formularioPagos!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort)  sort!: MatSort;

  
  pagoss: Pagos[] = [];
  pagos: Pagos = { } as Pagos;
  editar: boolean = false;
  idEditar: number | null = null;
  
  dataSource!: MatTableDataSource<Pagos>;
  mostrarColumnas: String[] =['idPago','metodo_pago','fecha_pago','acciones'];


  constructor(private pagosService: PagosService){ }

  
  ngOnInit(): void {
    this.findAll();
  }
  findAll(): void {
    this.pagosService.findAll().subscribe(data => {
      // this.Pagos = data;
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  save(): void{
    this.pagosService.save(this.pagos).subscribe(()=>{
      this.pagos = { } as Pagos;
      this.findAll();
    });
  }

  update():void{

    if(this.idEditar !==null){
      this.pagosService.update(this.idEditar,this.pagos).subscribe(()=>{
        this.pagos = { } as Pagos;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void{
    //this.PagosService.delete(this.Pagos.idPagos).subcribe(()=>{});

    Swal.fire({
      title: '¿Desea eliminar el dato?',
      text: 'Esta accion no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) =>{
      if(result.isConfirmed){
        this.pagosService.delete(this.pagos.idPago).subscribe(() =>{
          this.findAll();
          this.pagos = { } as Pagos;
          Swal.fire('Eliminado', 'El Pago ha sido eliminado', 'success');

        });
      } else {
        this.pagos = { } as Pagos;
      }
    });


  }

  //intercción con la PAGINA WEB

  editarPagos(pagos: Pagos): void{
    this.pagos = {...pagos};
    this.idEditar = pagos.idPago;
    this.editar = true;

    setTimeout(()=>{ 
      this.formularioPagos.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'});
    }, 100);

  }

  editarPagosCancelar(form: NgForm): void{
    this.pagos= { } as Pagos;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardar(form: NgForm): void{
    if(this.editar && this.idEditar !== null){
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
  }

  filtro(event: Event){
    const filtro1 = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro1.trim().toLowerCase();
  }


}
