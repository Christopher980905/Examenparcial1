import { TestBed } from '@angular/core/testing';

import { MovimientosInventarioService } from './movimientos-inventario';

describe('MovimientosInventario', () => {
  let service: MovimientosInventarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientosInventarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
