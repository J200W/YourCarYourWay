import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { UnauthGuard } from './core/guards/unauth.guard';
import { ChatComponent } from './chat/chat.component';
import { AuthComponent } from './auth/auth.component';

const routes: Routes = [
  {
    path: 'chat',
    canActivate: [AuthGuard],
    title: 'Chat | Your Car Your Way',
    component: ChatComponent,
  },
  {
    path: 'login',
    canActivate: [UnauthGuard],
    title: 'Se connecter | Your Car Your Way',
    component: AuthComponent,
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
