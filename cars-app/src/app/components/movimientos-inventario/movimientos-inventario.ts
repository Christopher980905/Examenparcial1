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
  libroSeleccionado: MovimientosInventario | null= null;


  mostrarColumnas: String[] = ['detalles', 'idMovimiento', 'tipo_movimiento', 'cantidad','fecha','productos', 'acciones'];
  
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
    title: '¿Desea eliminar el libro?',
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
        Swal.fire('Eliminado', 'El libro ha sido eliminado','success');
      });

    }else{
      this.movimientosinventario = { } as MovimientosInventario;
    }
  });
}


}
