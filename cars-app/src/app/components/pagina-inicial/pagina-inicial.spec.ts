import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaginaInicialComponent } from './pagina-inicial';

describe('PaginaInicialComponent', () => {
  let component: PaginaInicialComponent;
  let fixture: ComponentFixture<PaginaInicialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PaginaInicialComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaginaInicialComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
