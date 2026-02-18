import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Productos } from '../../model/productos';
import { MovimientosInventarioService } from '../../services/movimientos-inventario';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProductosService } from '../../services/productos';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { MovimientosInventario } from '../../model/movimientos-inventario';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-movimientos-inventario',
  standalone: false,
  templateUrl: './movimientos-inventario.html',
  styleUrl: './movimientos-inventario.css',
})
export class MovimientosInventarioComponent implements OnInit {

  movimientosinventarios: MovimientosInventario[]= [];
  productos: Productos[]=[];
  movimientosinventario: MovimientosInventario ={} as MovimientosInventario;
  editar: boolean = false;
  idEditar: number | null=null;
  dataSource!: MatTableDataSource<MovimientosInventario>;
  seleccionarArchivo!: File;
  imagenPrevia: string = "";
  movimientosinventarioSeleccionado: MovimientosInventario | null= null;


  mostrarColumnas: String[] = ['detalles', 'idMovimientoinventario', 'tipo_movimiento', 'cantidad','fecha','productos', 'acciones'];
  
  @ViewChild('formularioMovimientoInventario') formularioMovimientoInventario!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalMovimientoInventario') modalMovimientoInventario!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private movimientosinventarioService: MovimientosInventarioService,
    private productosService: ProductosService,
    private dialog: MatDialog,
    private http: HttpClient

  ){ }

  ngOnInit(): void {
    this.findAll();
    this.cargarProductos();
  }

  findAll(): void {
  
    this.movimientosinventarioService.findAll().subscribe(data => {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  })
}

cargarProductos():void{

this.productosService.findAll().subscribe(data => {this.productos = data;});
}

save(): void{
  this.movimientosinventarioService.save(this.movimientosinventario).subscribe(() =>{
    this.movimientosinventario = { } as MovimientosInventario;
    this.findAll();
  } );

}

update(): void{
  if(this.idEditar !== null){
    this.movimientosinventarioService.update(this.idEditar, this.movimientosinventario).subscribe(()=>{
      this.movimientosinventario = { } as MovimientosInventario;
      this.editar = false;
      this.idEditar = null;
      this.findAll();
    });
  }
}

delete(): void{
  Swal.fire({
    title: '¿Desea eliminar el movimiento?',
    text: 'Esta accion no se puede deshacer',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Si, Eliminar',
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  }).then((result)=>{
    if(result.isConfirmed){
      this.movimientosinventarioService.delete(this.movimientosinventario.idMovimientoinventario).subscribe(()=>{
        this.findAll();
        this.movimientosinventario = { } as MovimientosInventario;
        Swal.fire('Eliminado', 'El movimiento ha sido eliminado','success');
      });

    }else{
      this.movimientosinventario = { } as MovimientosInventario;
    }
  });
}
//interaccion en la pagina

editarMovimiento(movimientosinventario: MovimientosInventario): void{
  this.movimientosinventario = { ...movimientosinventario};
  this.idEditar = movimientosinventario.idMovimientoinventario;
  this.editar= true;
  setTimeout(() => {
    this.formularioMovimientoInventario.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'});
  },100);
}

editarMovimientoCancelar(form: NgForm):void{
  this.movimientosinventario= { } as MovimientosInventario;
  this.idEditar = null;
  this.editar = false;
  form.resetForm();
}

guardarMovimiento(): void{
  if(this.editar && this.idEditar !==null ){
    this.update();
  }else{
    this.save();
  }
  this.dialog.closeAll();
}

filtroMovimiento(event: Event): void{
  const filtro = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filtro.trim().toLocaleLowerCase();
}

nombreProducto(productos: Productos): string{
  return `${productos.nombre}`;

}
abrirModal(movimientosinventario?:MovimientosInventario):void{
  if(movimientosinventario){
    this.movimientosinventario = {...movimientosinventario};
    this.editar= true;
    this.idEditar= movimientosinventario.idMovimientoinventario;
  }else{
    this.movimientosinventario={ } as MovimientosInventario;
    this.editar= false;
    this.idEditar = null;
  }
  this.dialog.open(this.modalMovimientoInventario,{
    width: '800px',
  disableClose: true
  });

}

compareProductos(p1: Productos, p2: Productos): boolean{
  return p1 && p2 ? p1.idProducto === p2.idProducto : p1=== p2;
}

onFileSelected(event: any){
  this.seleccionarArchivo = event.target.files[0];
}

/*subirImagen(): void{
  const formData =new FormData();
  formData.append("file", this.seleccionarArchivo);

  if(this.libro.portada){
    formData.append("oldImage", this.libro.portada);
  }

  this.http.post<{ ruta: string }>('http://localhost:8081/api/upload-portada', formData).subscribe(res => {
    this.libro.portada = res.ruta;
    this.imagenPrevia = res.ruta;
  });
}*/

abrirModalDetalles(movimientosinventario: MovimientosInventario): void{
  this.movimientosinventarioSeleccionado = movimientosinventario;
  this.dialog.open(this.modalDetalles, {
    width:'500px'
  });
}

cerrarModal(): void{
  this.dialog.closeAll();
  this.movimientosinventarioSeleccionado = null;
}


}
