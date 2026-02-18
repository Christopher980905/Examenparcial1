import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ProductosService } from '../../services/productos';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CategoriaService } from '../../services/categoria';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { Productos } from '../../model/productos';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-productos',
  standalone: false,
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class ProductosComponent implements OnInit {
  productoss: Productos[]= [];
  categorias: Categoria[]=[];
  productos: Productos ={} as Productos;
  editar: boolean = false;
  idEditar: number | null=null;
  dataSource!: MatTableDataSource<Productos>;
  seleccionarArchivo: File | null = null; 
  imagenPrevia: string | null = null;
  productosSeleccionado: Productos | null= null;


  mostrarColumnas: String[] = ['detalles', 'idProducto', 'nombre', 'precio','stock','fecharegistro', 'categoria', 'acciones'];
  
  @ViewChild('formularioProductos') formularioProductos!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalProductos') modalProductos!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private productosService: ProductosService,
    private categoriaService: CategoriaService,
    private dialog: MatDialog,
    private http: HttpClient

  ){ }

  ngOnInit(): void {
    this.findAll();
    this.cargarCategorias();
  }

  findAll(): void {
  
    this.productosService.findAll().subscribe(data => {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  })
}

cargarCategorias():void{

this.categoriaService.findAll().subscribe(data => {this.categorias = data;});
}

save(): void{
  this.productosService.save(this.productos).subscribe(() =>{
    this.productos = { } as Productos;
    this.findAll();
  } );

}

update(): void{
  if(this.idEditar !== null){
    this.productosService.update(this.idEditar, this.productos).subscribe(()=>{
      this.productos = { } as Productos;
      this.editar = false;
      this.idEditar = null;
      this.findAll();
    });
  }
}

delete(): void{
  Swal.fire({
    title: '¿Desea eliminar el producto?',
    text: 'Esta accion no se puede deshacer',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Si, Eliminar',
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  }).then((result)=>{
    if(result.isConfirmed){
      this.productosService.delete(this.productos.idProducto).subscribe(()=>{
        this.findAll();
        this.productos = { } as Productos;
        Swal.fire('Eliminado', 'El producto ha sido eliminado','success');
      });

    }else{
      this.productos = { } as Productos;
    }
  });
}
 //interaccion en la pagina
 editarProducto(productos: Productos): void{
  this.productos = { ...productos};
  this.idEditar = productos.idProducto;
  this.editar= true;
  setTimeout(() => {
    this.formularioProductos.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'});
  },100);
}

editarProductoCancelar(form: NgForm):void{
  this.productos= { } as Productos;
  this.idEditar = null;
  this.editar = false;
  form.resetForm();
}

  guardarProducto(): void{
    if(this.editar && this.idEditar !==null ){
      this.update();
    }else{
      this.save();
    } 
    this.dialog.closeAll();
  }

filtroProducto(event: Event): void{
  const filtro = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filtro.trim().toLocaleLowerCase();
}

abrirModal(productos?:Productos):void{
  if(productos){
    this.productos = {...productos};
    this.editar= true;
    this.idEditar= productos.idProducto;
  }else{
    this.productos={ } as Productos;
    this.editar= false;
    this.idEditar = null;
  }
  this.dialog.open(this.modalProductos,{
    width: '800px',
  disableClose: true
  });

}
nombreCategoria(categoria: Categoria): string{
  return `${categoria.nombreCategoria}`;

}

compareCategorias(c1: Categoria, c2: Categoria): boolean{
  return c1 && c2 ? c1.idCategoria === c2.idCategoria : c1 === c2;
}

onFileSelected(event: any){
  this.seleccionarArchivo = event.target.files[0];

  if (this.seleccionarArchivo) {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagenPrevia = reader.result as string;
    };
    reader.readAsDataURL(this.seleccionarArchivo);
  }
}

subirImagen(): void{
  if (!this.seleccionarArchivo) return;
  const formData =new FormData();
  formData.append("file", this.seleccionarArchivo);

  if(this.productos.fondo){
    formData.append("oldImage", this.productos.fondo);
  }

  this.http.post<{ ruta: string }>('http://localhost:8081/api/uploads-fondos', formData).subscribe(res => {
    this.productos.fondo = res.ruta;
    this.imagenPrevia = null;         // limpiar preview
    this.seleccionarArchivo = null;   // limpiar selección de archivo
  });
}
 get imagenMostrar(): string | null {
    if (this.imagenPrevia) return this.imagenPrevia;
    if (this.productos?.fondo) return 'http://localhost:8081/' + this.productos.fondo;
    return null;
  }

abrirModalDetalles(productos: Productos): void{
  this.productosSeleccionado = productos;
  this.dialog.open(this.modalDetalles, {
    width:'500px'
  });
}

cerrarModal(): void{
  this.dialog.closeAll();
  this.productosSeleccionado = null;
}
get fondoSeleccionada(): string | null {
  return this.productosSeleccionado?.fondo ? 'http://localhost:8081/' + this.productosSeleccionado.fondo : null;
}
 
}
