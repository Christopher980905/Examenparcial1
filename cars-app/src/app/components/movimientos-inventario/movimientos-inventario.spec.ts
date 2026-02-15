import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientosInventarioComponent } from './movimientos-inventario';

describe('MovimientosInventarioComponent', () => {
  let component: MovimientosInventarioComponent;
  let fixture: ComponentFixture<MovimientosInventarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MovimientosInventarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovimientosInventarioComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
