///*
//* You should annotate your controller with @Resource, so all of its public methods will
//* be ready to deal with web requests.
//*/
//@Resource
//public class ClientsController {
//
//    private ClientDao dao;
//
//    /*
//     * You can get your class dependencies through constructor, and VRaptor will be in charge
//     * of creating or locating these dependencies and manage them to create your controller.
//     * If you want that VRaptor3 manages creation of ClientDao, you should annotate it with
//     * @Component
//     */
//    public ClientsController(ClientDao dao) {
//        this.dao = dao;
//    }
//
//    /*
//     * All public methods from your controller will be reachable through web.
//     * For example, form method can be accessed by URI /clients/form,
//     * and will render the view /WEB-INF/jsp/clients/form.jsp
//     */
//    public void form() {
//        // code that loads data for checkboxes, selects, etc
//    }
//
//    /*
//     * You can receive parameters on your method, and VRaptor will set your parameters
//     * fields with request parameters. If the request have:
//     * custom.name=Lucas
//     * custom.address=Vergueiro Street
//     * VRaptor will set the fields name and address of Client custom with values
//     * "Lucas" and "Vergueiro Street", using the fields setters.
//     * URI: /clients/add
//     * view: /WEB-INF/jsp/clients/add.jsp
//     */
//    public void add(Client custom) {
//        dao.save(custom);
//    }
//
//    /*
//     * VRaptor will export your method return value to the view. In this case,
//     * since your method return type is List<Clients>, then you can access the
//     * returned value on your jsp with the variable ${clientList}
//     * URI: /clients/list
//     * view: /WEB-INF/jsp/clients/list.jsp
//     */
//    public List<Client> list() {
//        return dao.listAll():
//    }
//
//    /*
//     * If the return type is a simple type, the name of exported variable will be
//     * the class name with the first letter in lower case. Since this method return
//     * type is Client, the variable will be ${client}.
//     * A request parameter would be something like id=5, and then VRaptor is able
//     * to get this value, convert it to Long, and pass it as parameter to your method.
//     * URI: /clients/view
//     * view: /WEB-INF/jsp/clients/view.jsp
//     */
//    public Client view(Long id) {
//        return dao.load(id);
//    }
//}