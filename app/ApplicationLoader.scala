import controllers.AssetsComponents
import controllers.ViteController
import router.Routes

import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import play.api.libs.ws.ahc.AhcWSComponents

class ViteExampleApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {
    new ViteExampleComponents(context).application
  }
}

class ViteExampleComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with AssetsComponents
  with HttpFiltersComponents
  with AhcWSComponents
{
  val mode: Mode = context.environment.mode

  val viteController = new ViteController(controllerComponents, wsClient, assets, mode)(controllerComponents.executionContext)

  def router: Router = new Routes(
    httpErrorHandler,
    viteController
  )
}
