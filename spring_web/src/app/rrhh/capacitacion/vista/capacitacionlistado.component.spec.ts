import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CapacitacionListadoComponent } from './capacitacion-listado.component';


describe('CapacitacionListadoComponent', () => {
  let component: CapacitacionListadoComponent;
  let fixture: ComponentFixture<CapacitacionListadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapacitacionListadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CapacitacionListadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
