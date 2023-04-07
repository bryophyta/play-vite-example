import controllers.HomeController
import controllers.AssetsComponents
import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import router.Routes
import play.filters.HttpFiltersComponents

class ViteExampleApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {
    new ViteExampleComponents(context).application
  }
}

class ViteExampleComponents(context: Context) extends BuiltInComponentsFromContext(context) with AssetsComponents with HttpFiltersComponents {
  val mode: Mode = context.environment.mode
  val homeController = new HomeController(controllerComponents, mode)

  def router: Router = new Routes(
    httpErrorHandler,
    homeController,
    assets
  )
}
