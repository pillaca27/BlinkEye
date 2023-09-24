import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Especialidad2MantenimientoComponent } from './especialidad2-mantenimiento.component';

describe('Especialidad2MantenimientoComponent', () => {
  let component: Especialidad2MantenimientoComponent;
  let fixture: ComponentFixture<Especialidad2MantenimientoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Especialidad2MantenimientoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Especialidad2MantenimientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
