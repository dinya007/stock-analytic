import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PlotComponent} from './plot/plot.component';
import {MainComponent} from './main/main.component';


const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'plot', component: PlotComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
